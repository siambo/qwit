package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Hashtag(
    @field:Json(name = "indices")
    val indices: List<Int>,
    @field:Json(name = "text")
    val text: String
)
