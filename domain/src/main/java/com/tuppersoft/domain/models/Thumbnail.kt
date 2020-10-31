package com.tuppersoft.domain.models

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class Thumbnail(
    @Json(name = "extension")
    val extension: String?,
    @Json(name = "path")
    val path: String?
) : Serializable
