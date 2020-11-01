package com.tuppersoft.domain.models.character


import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@Keep
data class Item(
    @Json(name = "name")
    val name: String?,
    @Json(name = "resourceURI")
    val resourceURI: String?,
    @Json(name = "type")
    val type: String?
): Serializable
