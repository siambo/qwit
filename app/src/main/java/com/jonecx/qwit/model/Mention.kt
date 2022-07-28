package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Mention(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "id_str")
    val idStr: String,
    @field:Json(name = "indices")
    val indices: List<Int>,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "screen_name")
    val screenName: String
)
