package com.tuppersoft.domain.models

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class MarvelResult(
    @Json(name = "attributionHTML")
    val attributionHTML: String?,
    @Json(name = "attributionText")
    val attributionText: String?,
    @Json(name = "code")
    val code: Int?,
    @Json(name = "copyright")
    val copyright: String?,
    @Json(name = "data")
    val `data`: Data?,
    @Json(name = "etag")
    val etag: String?,
    @Json(name = "status")
    val status: String?
): Serializable


