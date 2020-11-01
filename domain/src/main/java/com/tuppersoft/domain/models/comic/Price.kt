package com.tuppersoft.domain.models.comic


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class Price(
    @Json(name = "price")
    val price: Double? = 0.0,
    @Json(name = "type")
    val type: String? = ""
)
