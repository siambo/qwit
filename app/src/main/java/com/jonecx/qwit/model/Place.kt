package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Place(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "place_type")
    val placeType: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "full_name")
    val fullName: String,
    @field:Json(name = "country_code")
    val countryCode: String,
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "bounding_box")
    val boundingBox: BoundingBox,
    @field:Json(name = "attributes")
    val attributes: Attributes
)
