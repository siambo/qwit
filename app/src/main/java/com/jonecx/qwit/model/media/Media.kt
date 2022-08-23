package com.jonecx.qwit.model.media

import com.squareup.moshi.Json

data class Media(
    @field:Json(name = "display_url")
    val displayUrl: String,
    @field:Json(name = "expanded_url")
    val expandedUrl: String,
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "idStr")
    val idStr: String,
    @field:Json(name = "indices")
    val indices: List<Int>,
    @field:Json(name = "media_url")
    val mediaUrl: String,
    @field:Json(name = "media_url_https")
    val mediaUrlHttps: String,
    @field:Json(name = "sizes")
    val size: Size,
    @field:Json(name = "source_status_id")
    val sourceStatusId: Long?,
    @field:Json(name = "source_status_id_str")
    val sourceStatusIdStr: String?,
    @field:Json(name = "type")
    val type: MediaType,
    @field:Json(name = "url")
    val wrappedUrl: String
)
