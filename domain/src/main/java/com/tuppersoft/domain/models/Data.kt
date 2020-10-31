package com.tuppersoft.domain.models

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class Data(
    @Json(name = "count")
    val count: Int? = 0,
    @Json(name = "limit")
    val limit: Int? = 0,
    @Json(name = "offset")
    val offset: Int? = 0,
    @Json(name = "results")
    val results: List<Characters> = listOf(),
    @Json(name = "total")
    val total: Int? = 0
): Serializable



