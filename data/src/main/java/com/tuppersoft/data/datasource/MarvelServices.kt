package com.tuppersoft.data.datasource

import com.tuppersoft.data.datasource.MarvelEndPoints.PATH_PARAM_CHARACTER_ID
import com.tuppersoft.domain.models.character.CharacterResult
import com.tuppersoft.domain.models.comic.ComicResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelServices {

    @GET(MarvelEndPoints.BASE_URL + MarvelEndPoints.characters)
    suspend fun getCharacters(@Query("limit") limit: Int = 100, @Query("offset") offset: Int = 0): Response<CharacterResult>

    @GET(MarvelEndPoints.BASE_URL + MarvelEndPoints.comicsByCharacter)
    suspend fun getComicsByCharacters(@Path(PATH_PARAM_CHARACTER_ID) id: String): Response<ComicResult>
}
