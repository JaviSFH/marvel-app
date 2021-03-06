package com.tuppersoft.domain.models.comic


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class Variant(
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "resourceURI")
    val resourceURI: String? = ""
)
