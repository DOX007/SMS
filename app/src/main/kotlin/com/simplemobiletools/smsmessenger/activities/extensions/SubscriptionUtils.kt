package com.simplemobiletools.smsmessenger.extensions

import android.content.Context
import android.telephony.SubscriptionManager

fun getDefaultSubscriptionId(context: Context): Int {
    val subscriptionManager = context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager
    return SubscriptionManager.getDefaultSmsSubscriptionId()
}
