package com.jonecx.qwit.util

import android.net.Uri
import android.os.Build
import com.jonecx.qwit.BuildConfig

fun Uri?.isAuthCallback(): Boolean {
    return this != null && this.toString().contains(BuildConfig.CALLBACK_URL)
}

fun String?.orString(text: String): String =
    if (this != null && this.isNotBlank() && this.isNotEmpty()) this else text

fun isOS12() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
