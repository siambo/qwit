package com.jonecx.qwit.model.media

import com.squareup.moshi.Json

data class Media(
    @Json(name = "display_url")
    val displayUrl: String,
    @Json(name = "expanded_url")
    val expandedUrl: String,
    @Json(name = "id")
    val id: Long,
    @Json(name = "idStr")
    val idStr: String,
    @Json(name = "indices")
    val indices: List<Int>,
    @Json(name = "media_url")
    val mediaUrl: String,
    @Json(name = "media_url_https")
    val mediaUrlHttps: String,
    @Json(name = "sizes")
    val size: MediaSize,
    @Json(name = "source_status_id")
    val sourceStatusId: Long?,
    @Json(name = "source_status_id_str")
    val sourceStatusIdStr: String?,
    @Json(name = "type")
    val type: MediaType,
    @Json(name = "url")
    val wrappedUrl: String
)
