package com.jonecx.qwit.model.media

import com.squareup.moshi.Json

data class Size(
    @field:Json(name = "thumb")
    val thumb: Thumb,
    @field:Json(name = "small")
    val small: Small,
    @field:Json(name = "medium")
    val medium: Medium,
    @field:Json(name = "large")
    val large: Large,
    @field:Json(name = "resize")
    val resize: Resize)

interface ActualSize {
    val width: Int
    val height: Int
    val resize: Resize
}

data class Thumb(
    @field:Json(name = "w")
    override val width: Int,
    @field:Json(name = "h")
    override val height: Int,
    @field:Json(name = "resize")
    override val resize: Resize
) : ActualSize
data class Small(
    @field:Json(name = "w")
    override val width: Int,
    @field:Json(name = "h")
    override val height: Int,
    @field:Json(name = "resize")
    override val resize: Resize
) : ActualSize
data class Medium(
    @field:Json(name = "w")
    override val width: Int,
    @field:Json(name = "h")
    override val height: Int,
    @field:Json(name = "resize")
    override val resize: Resize
) : ActualSize
data class Large(
    @field:Json(name = "w")
    override val width: Int,
    @field:Json(name = "h")
    override val height: Int,
    @field:Json(name = "resize")
    override val resize: Resize
) : ActualSize


