package com.jonecx.qwit.model.media

import com.squareup.moshi.Json

data class Url(
    @field:Json(name = "display_url")
    val displayUrl: String,
    @field:Json(name = "expanded_url")
    val expandedUrl: String,
    @field:Json(name = "indices")
    val indices: List<Int>,
    @field:Json(name = "url")
    val url: String
)
