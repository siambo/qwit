package com.jonecx.qwit.model

import com.jonecx.qwit.model.media.Media
import com.squareup.moshi.Json

data class ExtendedEntities(
    @Json(name = "media")
    val media: List<Media>
)
