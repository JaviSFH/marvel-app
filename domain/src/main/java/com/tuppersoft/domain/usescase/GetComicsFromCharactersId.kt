package com.tuppersoft.domain.usescase

import com.tuppersoft.domain.baseusecase.GlobalUseCase
import com.tuppersoft.domain.models.character.CharacterData
import com.tuppersoft.domain.models.comic.ComicData
import com.tuppersoft.domain.repository.MarvelRepository
import com.tuppersoft.domain.usescase.GetComicsFromCharactersId.Params
import kotlinx.coroutines.flow.Flow

class GetComicsFromCharactersId constructor(private val repository: MarvelRepository) :
    GlobalUseCase<Flow<ComicData>, Params>() {

    data class Params(val characterId: String)

    override suspend fun run(params: Params): Flow<ComicData> {
        return repository.getComicsFromCharacter(params.characterId)
    }
}
