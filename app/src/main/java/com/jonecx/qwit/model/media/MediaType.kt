package com.jonecx.qwit.model.media

import com.squareup.moshi.Json

enum class MediaType {
    @field:Json(name = "photo")
    Photo,
    @field:Json(name = "video")
    Video,
    @field:Json(name = "animated_gif")
    AnimatedGif
}
