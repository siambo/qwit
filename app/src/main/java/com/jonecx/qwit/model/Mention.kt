package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Mention(
    @Json(name = "id")
    val id: Long,
    @Json(name = "id_str")
    val idStr: String,
    @Json(name = "indices")
    val indices: List<Int>,
    @Json(name = "name")
    val name: String,
    @Json(name = "screen_name")
    val screenName: String
)
