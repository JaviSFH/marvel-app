package com.tuppersoft.marvel.core.di

import com.tuppersoft.data.repositories.MarvelRepositoryImpl
import com.tuppersoft.domain.usescase.GetCharacters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideGetCharacters(dataSource: MarvelRepositoryImpl): GetCharacters = GetCharacters(dataSource)
}

