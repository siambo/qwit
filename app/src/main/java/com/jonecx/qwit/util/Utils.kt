package com.jonecx.qwit.util

import android.net.Uri
import android.os.Build
import android.text.format.DateFormat
import android.text.format.DateUtils
import androidx.compose.ui.platform.LocalContext
import com.jonecx.qwit.BuildConfig
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.time.temporal.TemporalField
import java.time.temporal.TemporalQueries.localDate
import java.util.*


private val oneSecond = 1000L
private val oneMinute = oneSecond * 60
private val oneHour = oneMinute * 60
private val oneDay = oneHour * 24
private val twoDay = oneDay * 2
fun Uri?.isAuthCallback(): Boolean {
    return this != null && this.toString().contains(BuildConfig.CALLBACK_URL)
}

fun String?.orString(text: String): String =
    if (this != null && this.isNotBlank() && this.isNotEmpty()) this else text

fun isOS12() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S


fun String.toDate(): String {

    val formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss Z yyyy")
    val dateTime: LocalDateTime = LocalDateTime.parse(this, formatter)
    val simpleFormat = SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.getDefault())
    val xyz = simpleFormat.parse(this)?.time

    val timeInLong = dateTime.toEpochSecond(ZoneOffset.UTC)
    val diff = System.currentTimeMillis()// - timeInLong
    return if (diff < oneMinute)
        "${(diff / 1000L)}s"
    else if (diff < oneHour)
        "5m"
    else if (diff < oneDay)
        "23h"
    else if (diff < twoDay) {
        "1d"
    } else {
        val dateFormatter = SimpleDateFormat("dd MMM yy", Locale.getDefault())
        dateFormatter.format(xyz)
    }
}