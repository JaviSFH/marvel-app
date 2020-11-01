package com.tuppersoft.domain.models.comic


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class ComicResult(
    @Json(name = "attributionHTML")
    val attributionHTML: String? = "",
    @Json(name = "attributionText")
    val attributionText: String? = "",
    @Json(name = "code")
    val code: Int? = 0,
    @Json(name = "copyright")
    val copyright: String? = "",
    @Json(name = "data")
    val `data`: ComicData? = ComicData(),
    @Json(name = "etag")
    val etag: String? = "",
    @Json(name = "status")
    val status: String? = ""
)
