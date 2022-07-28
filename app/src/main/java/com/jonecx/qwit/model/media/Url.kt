package com.jonecx.qwit.model.media

import com.squareup.moshi.Json

data class Url(
    @Json(name = "display_url")
    val displayUrl: String,
    @Json(name = "display_url")
    val expandedUrl: String,
    @Json(name = "indices")
    val indices: List<Int>,
    @Json(name = "url")
    val url: String
)
