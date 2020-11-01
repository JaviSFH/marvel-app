package com.tuppersoft.data.repositories

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi.Builder
import com.tuppersoft.data.datasource.MarvelServices
import com.tuppersoft.data.functions.CheckInternet
import com.tuppersoft.domain.models.character.CharacterData
import com.tuppersoft.domain.models.comic.ComicData
import com.tuppersoft.domain.models.exceptions.Failure
import com.tuppersoft.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelServices,
    private val checkInternet: CheckInternet
) : MarvelRepository {

    override suspend fun getCharacters(limit: Int, offset: Int): Flow<CharacterData> {
        return flow {

            checkInternet.checkInternetConnection()

            try {
                val response = api.getCharacters(limit, offset)

                if (response.isSuccessful) {
                    response.body()
                } else {
                    val error = response.errorBody()?.string()?.let { parseError(it) }
                    throw Failure.OthersException("Network error " + response.code() + ". " + error?.code + ". " + error?.message)
                }

                emit(response.body()?.data ?: CharacterData())
            } catch (e: Exception) {
                throw Failure.OthersException(e.message.orEmpty())
            }
        }
    }

    override suspend fun getComicsFromCharacter(characterId: String): Flow<ComicData> {
        return flow {

            checkInternet.checkInternetConnection()

            try {
                val response = api.getComicsByCharacters(characterId)

                if (response.isSuccessful) {
                    response.body()
                } else {
                    val error = response.errorBody()?.string()?.let { parseError(it) }
                    throw Failure.OthersException("Network error " + response.code() + ". " + error?.code + ". " + error?.message)
                }

                emit(response.body()?.data ?: ComicData())
            } catch (e: Exception) {
                throw Failure.OthersException(e.message.orEmpty())
            }
        }
    }

    private fun parseError(errorBody: String): com.tuppersoft.domain.models.error.Error? {

        val moshi = Builder().build()
        val jsonAdapter: JsonAdapter<com.tuppersoft.domain.models.error.Error> =
            moshi.adapter(com.tuppersoft.domain.models.error.Error::class.java)

        return jsonAdapter.fromJson(errorBody)
    }
}


