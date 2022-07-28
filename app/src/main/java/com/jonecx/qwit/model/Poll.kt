package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Poll(
    @field:Json(name = "options")
    val options: List<Option>,
    @field:Json(name = "end_datetime")
    val endDatetime: String,
    @field:Json(name = "duration_minutes")
    val durationInMinutes: String
)
