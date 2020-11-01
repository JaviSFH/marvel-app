package com.tuppersoft.domain.models.comic


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class Date(
    @Json(name = "date")
    val date: String? = "",
    @Json(name = "type")
    val type: String? = ""
)
