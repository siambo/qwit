package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Option(
    @field:Json(name = "position")
    val position: Int,
    @field:Json(name = "text")
    val text: String
)
