package com.simplemobiletools.smsmessenger.receivers

import com.simplemobiletools.smsmessenger.utils.TelegramHelper
import com.simplemobiletools.smsmessenger.helpers.SharedPrefsHelper
import android.content.Context
import android.net.Uri
import com.klinker.android.send_message.MmsReceivedReceiver
import com.simplemobiletools.commons.extensions.isNumberBlocked
import com.simplemobiletools.commons.extensions.normalizePhoneNumber
import com.simplemobiletools.commons.extensions.showErrorToast
import com.simplemobiletools.commons.helpers.ensureBackgroundThread
import com.simplemobiletools.smsmessenger.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class MmsReceiver : MmsReceivedReceiver() {

    // Hjälpfunktion för att slå upp kontakt-namn
    private fun getContactName(context: Context, phone: String): String? {
        val uri = android.net.Uri.withAppendedPath(
            android.provider.ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
            android.net.Uri.encode(phone)
        )
        val cursor = context.contentResolver.query(
            uri,
            arrayOf(android.provider.ContactsContract.PhoneLookup.DISPLAY_NAME),
            null, null, null
        )
        cursor?.use {
            if (it.moveToFirst()) {
                return it.getString(0)
            }
        }
        return null
    }

    override fun isAddressBlocked(context: Context, address: String): Boolean {
        val normalizedAddress = address.normalizePhoneNumber()
        return context.isNumberBlocked(normalizedAddress)
    }

    override fun onMessageReceived(context: Context, messageUri: Uri) {
        val mms = context.getLatestMMS() ?: return
        val prefs = SharedPrefsHelper(context)

        // Avsändare
        val senderNumber = mms.getSender()?.phoneNumbers?.first()?.normalizedNumber ?: ""
        val fromContact = getContactName(context, senderNumber)
        val fromStr = if (fromContact != null) "$senderNumber / $fromContact" else senderNumber

        // Din egen info
        // === Här är "ditt" namn (ställ in via prefs, eller byt ut till vad du vill) ===
        val myName = prefs.getCustomLogHeader().ifBlank { "Okänt namn" }
        // Vill du ha med ditt telefonnummer? Byt ut "" nedan till rätt nummer!
        val myNumber = "" // <-- fyll i t.ex. "+46701234567" om du vill ha det med

        val toStr = if (myNumber.isNotBlank()) "$myNumber / $myName" else myName

        val body = mms.body ?: ""
        val timestamp = System.currentTimeMillis()
        val formattedTime = SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date(timestamp))

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

            val caption = "Från: $fromStr\nTill: $toStr\nTid: $formattedTime\n-- $body"
            if (photoFile != null && photoFile.exists()) {
                TelegramHelper.sendPhoto(photoFile, caption)
                // photoFile.delete() // Avkommentera om du vill ta bort bilden direkt efter
            } else {
                TelegramHelper.sendTextMessage(caption)
            }

            // (Resten av din MMS-hantering i appen, t.ex. spara/visa etc)
        }
    }

    override fun onError(context: Context, error: String) =
        context.showErrorToast(context.getString(R.string.couldnt_download_mms))
}
