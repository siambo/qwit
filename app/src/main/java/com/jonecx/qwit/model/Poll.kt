package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Poll(
    @Json(name = "options")
    val options: List<Option>,
    @Json(name = "end_datetime")
    val endDatetime: String,
    @Json(name = "duration_minutes")
    val durationInMinutes: String
)
