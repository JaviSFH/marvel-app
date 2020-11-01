package com.tuppersoft.domain.models.comic


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class Url(
    @Json(name = "type")
    val type: String? = "",
    @Json(name = "url")
    val url: String? = ""
)
