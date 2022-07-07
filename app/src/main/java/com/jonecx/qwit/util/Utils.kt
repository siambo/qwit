package com.jonecx.qwit.util

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import com.jonecx.qwit.BuildConfig

fun Uri?.isAuthCallback(): Boolean {
    return this != null && this.toString().contains(BuildConfig.CALLBACK_URL)
}

fun ComponentActivity.navigateOneWayTo(toActivity: Class<*>) {
    val intent = Intent(this, toActivity)
    this.startActivity(intent)
    this.finish()
}
