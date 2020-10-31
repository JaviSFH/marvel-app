package com.tuppersoft.domain.models

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@Keep
data class Series(
    @Json(name = "available")
    val available: Int?,
    @Json(name = "collectionURI")
    val collectionURI: String?,
    @Json(name = "items")
    val items: List<Item> = listOf(),
    @Json(name = "returned")
    val returned: Int?
): Serializable
