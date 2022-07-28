package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class Location(
    @Json(name = "country")
    val country: String,
    @Json(name = "country_code")
    val countryCode: String,
    @Json(name = "locality")
    val locality: String,
    @Json(name = "region")
    val region: String,
    @Json(name = "sub_region")
    val subRegion: String,
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "geo")
    val geo: Geo
)
