package com.tuppersoft.domain.models.error

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Error(
    @Json(name = "code")
    val code: String? = "",
    @Json(name = "message")
    val message: String? = ""
)
