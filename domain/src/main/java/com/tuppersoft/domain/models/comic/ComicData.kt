package com.tuppersoft.domain.models.comic


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class ComicData(
    @Json(name = "count")
    val count: Int? = 0,
    @Json(name = "limit")
    val limit: Int? = 0,
    @Json(name = "offset")
    val offset: Int? = 0,
    @Json(name = "results")
    val results: List<Comic>? = listOf(),
    @Json(name = "total")
    val total: Int? = 0
)
