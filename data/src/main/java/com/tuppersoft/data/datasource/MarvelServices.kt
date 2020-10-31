package com.tuppersoft.data.datasource

import com.tuppersoft.domain.models.MarvelResult
import retrofit2.Response
import retrofit2.http.GET

interface MarvelServices {

    @GET(MarvelEndPoints.BASE_URL + MarvelEndPoints.characters)
    suspend fun getCharacters(): Response<MarvelResult>
}
