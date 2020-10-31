package com.tuppersoft.domain.usescase

import com.tuppersoft.domain.baseusecase.GlobalUseCase
import com.tuppersoft.domain.baseusecase.GlobalUseCase.None
import com.tuppersoft.domain.models.Data
import com.tuppersoft.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow

class GetCharacters constructor(private val repository: MarvelRepository) : GlobalUseCase<Flow<Data>, None>() {

    override suspend fun run(params: None): Flow<Data> {
        return repository.getCharacters()
    }
}
