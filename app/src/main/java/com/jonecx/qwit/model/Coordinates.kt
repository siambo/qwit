package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Coordinates(
    @Json(name = "coordinates")
    val coordinates: List<Double>,
    @Json(name = "type")
    val type: String?
)
