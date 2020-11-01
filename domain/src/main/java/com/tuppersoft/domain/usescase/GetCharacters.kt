package com.tuppersoft.domain.usescase

import com.tuppersoft.domain.baseusecase.GlobalUseCase
import com.tuppersoft.domain.models.character.CharacterData
import com.tuppersoft.domain.repository.MarvelRepository
import com.tuppersoft.domain.usescase.GetCharacters.Params
import kotlinx.coroutines.flow.Flow

class GetCharacters constructor(private val repository: MarvelRepository) :
    GlobalUseCase<Flow<CharacterData>, Params>() {

    data class Params(val limit: Int = 100, val offset: Int = 0)

    override suspend fun run(params: Params): Flow<CharacterData> {
        return repository.getCharacters(params.limit, params.offset)
    }
}
