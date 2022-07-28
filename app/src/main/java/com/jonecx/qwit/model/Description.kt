package com.jonecx.qwit.model

import com.jonecx.qwit.model.media.Url
import com.squareup.moshi.Json

data class Description(
    @field:Json(name = "urls")
    val urls: List<Url>
)
