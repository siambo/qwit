package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class BoundingBox(
    @Json(name = "coordinates")
    val coordinates: List<List<List<Double>>>,
    @Json(name = "type")
    val type: String,
)
