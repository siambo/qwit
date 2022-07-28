package com.jonecx.qwit.model

import com.jonecx.qwit.model.media.Media
import com.jonecx.qwit.model.media.Url
import com.squareup.moshi.Json

data class Entities(
    @field:Json(name = "hashtags")
    val hashtags: List<Hashtag>,
    @field:Json(name = "media")
    val media: List<Media>,
    @field:Json(name = "urls")
    val urls: List<Url>,
    @field:Json(name = "user_mentions")
    val mentions: List<Mention>,
    @field:Json(name = "symbols")
    val symbols: List<Symbol>,
    @field:Json(name = "polls")
    val polls: List<Poll>
)
