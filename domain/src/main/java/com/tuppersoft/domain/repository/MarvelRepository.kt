package com.tuppersoft.domain.repository

import com.tuppersoft.domain.models.character.CharacterData
import com.tuppersoft.domain.models.comic.ComicData
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

    suspend fun getCharacters(limit: Int = 100, offset: Int = 0): Flow<CharacterData>
    suspend fun getComicsFromCharacter(characterId: String): Flow<ComicData>
}
