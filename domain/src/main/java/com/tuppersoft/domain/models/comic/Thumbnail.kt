package com.tuppersoft.domain.models.comic


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class Thumbnail(
    @Json(name = "extension")
    val extension: String? = "",
    @Json(name = "path")
    val path: String? = ""
)
