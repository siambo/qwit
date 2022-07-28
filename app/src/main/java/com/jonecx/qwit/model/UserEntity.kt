package com.jonecx.qwit.model

import com.jonecx.qwit.model.media.Url
import com.squareup.moshi.Json

data class UserEntity(
    @field:Json(name = "urls")
    var urls: List<Url>,
    @field:Json(name = "description")
    var description: Description
)
