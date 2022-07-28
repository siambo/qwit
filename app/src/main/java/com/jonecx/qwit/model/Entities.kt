package com.jonecx.qwit.model

import com.jonecx.qwit.model.media.Media
import com.jonecx.qwit.model.media.Url
import com.squareup.moshi.Json

data class Entities(
    @Json(name = "hashtags")
    val hashtags: List<Hashtag>,
    @Json(name = "media")
    val media: List<Media>,
    @Json(name = "urls")
    val urls: List<Url>,
    @Json(name = "user_mentions")
    val mentions: List<Mention>,
    @Json(name = "symbols")
    val symbols: List<Symbol>,
    @Json(name = "polls")
    val polls: List<Poll>
)
