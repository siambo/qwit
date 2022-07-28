package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Coordinates(
    @field:Json(name = "coordinates")
    val coordinates: List<Double>,
    @field:Json(name = "type")
    val type: String?
)
