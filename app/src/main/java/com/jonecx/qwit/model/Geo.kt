package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Geo(
    @Json(name = "coordinates")
    val geo: Coordinates,
    @Json(name = "type")
    val type: String
)
