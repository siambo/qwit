package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Option(
    @Json(name = "position")
    val position: Int,
    @Json(name = "text")
    val text: String
)
