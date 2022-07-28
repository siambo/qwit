package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class MatchingRule(
    @Json(name = "tag")
    val tag: String?,
    @Json(name = "id")
    val id: Long?,
    @Json(name = "id_str")
    val idStr: String,
)
