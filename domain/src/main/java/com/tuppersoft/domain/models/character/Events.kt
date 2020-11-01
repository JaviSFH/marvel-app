package com.tuppersoft.domain.models.character

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class Events(
    @Json(name = "available")
    val available: Int?,
    @Json(name = "collectionURI")
    val collectionURI: String?,
    @Json(name = "items")
    val items: List<Item> = listOf(),
    @Json(name = "returned")
    val returned: Int?
): Serializable
