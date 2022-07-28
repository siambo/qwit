package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Place(
    @Json(name = "id")
    val id: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "place_type")
    val placeType: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "country_code")
    val countryCode: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "bounding_box")
    val boundingBox: BoundingBox,
    @Json(name = "attributes")
    val attributes: Attributes
)
