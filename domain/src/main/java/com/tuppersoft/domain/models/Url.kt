package com.tuppersoft.domain.models

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class Url(
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?
) : Serializable
