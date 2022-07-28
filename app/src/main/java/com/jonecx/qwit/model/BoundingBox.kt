package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class BoundingBox(
    @field:Json(name = "coordinates")
    val coordinates: List<List<List<Double>>>,
    @field:Json(name = "type")
    val type: String,
)
