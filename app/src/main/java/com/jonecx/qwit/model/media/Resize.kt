package com.jonecx.qwit.model.media

import com.squareup.moshi.Json

enum class Resize {
    @field:Json(name = "fit")
    Fit,
    @field:Json(name = "crop")
    Crop
}
