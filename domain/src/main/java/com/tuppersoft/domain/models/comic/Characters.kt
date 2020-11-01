package com.tuppersoft.domain.models.comic


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class Characters(
    @Json(name = "available")
    val available: Int? = 0,
    @Json(name = "collectionURI")
    val collectionURI: String? = "",
    @Json(name = "items")
    val items: List<Item>? = listOf(),
    @Json(name = "returned")
    val returned: Int? = 0
)
