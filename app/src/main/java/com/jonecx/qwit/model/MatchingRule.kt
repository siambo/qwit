package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class MatchingRule(
    @field:Json(name = "tag")
    val tag: String?,
    @field:Json(name = "id")
    val id: Long?,
    @field:Json(name = "id_str")
    val idStr: String,
)
