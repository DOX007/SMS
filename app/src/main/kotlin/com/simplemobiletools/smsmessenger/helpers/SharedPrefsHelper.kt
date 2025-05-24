package com.simplemobiletools.smsmessenger.helpers

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefsHelper(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("sms_prefs", Context.MODE_PRIVATE)

    fun isWhitelistingEnabled(): Boolean {
        return prefs.getBoolean("whitelisting_enabled", false)
    }

    fun setWhitelistingEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("whitelisting_enabled", enabled).apply()
    }

    fun getWhitelistedNumbers(): List<String> {
        val json = prefs.getString("whitelist_numbers", "[]") ?: "[]"
        return Gson().fromJson(json, object : TypeToken<List<String>>() {}.type)
    }

    fun setWhitelistedNumbers(numbers: List<String>) {
        val json = Gson().toJson(numbers)
        prefs.edit().putString("whitelist_numbers", json).apply()
    }

    fun setCustomLogHeader(header: String) {
        prefs.edit().putString("custom_log_header", header).apply()
    }

    fun getCustomLogHeader(): String {
        return prefs.getString("custom_log_header", "") ?: ""
    }
}
