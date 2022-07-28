package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Symbol(
    @Json(name = "indices")
    val indices: List<Int>,
    @Json(name = "text")
    val text: String,
)
