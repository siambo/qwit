package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Geo(
    @field:Json(name = "coordinates")
    val geo: Coordinates,
    @field:Json(name = "type")
    val type: String
)
