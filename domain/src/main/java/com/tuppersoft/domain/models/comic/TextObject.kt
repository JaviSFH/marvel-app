package com.tuppersoft.domain.models.comic


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class TextObject(
    @Json(name = "language")
    val language: String? = "",
    @Json(name = "text")
    val text: String? = "",
    @Json(name = "type")
    val type: String? = ""
)
