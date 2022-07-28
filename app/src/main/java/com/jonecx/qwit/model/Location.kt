package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Location(
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "country_code")
    val countryCode: String,
    @field:Json(name = "locality")
    val locality: String,
    @field:Json(name = "region")
    val region: String,
    @field:Json(name = "sub_region")
    val subRegion: String,
    @field:Json(name = "full_name")
    val fullName: String,
    @field:Json(name = "geo")
    val geo: Geo
)
