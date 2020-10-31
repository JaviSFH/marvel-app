package com.tuppersoft.domain.repository

import com.tuppersoft.domain.models.Data
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

    suspend fun getCharacters(): Flow<Data>
}
