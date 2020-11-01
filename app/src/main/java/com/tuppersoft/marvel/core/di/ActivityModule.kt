package com.tuppersoft.marvel.core.di

import com.tuppersoft.data.datasource.MarvelServices
import com.tuppersoft.data.repositories.MarvelRepositoryImpl
import com.tuppersoft.domain.usescase.GetCharacters
import com.tuppersoft.domain.usescase.GetComicsFromCharactersId
import com.tuppersoft.marvel.core.platform.CheckInternetConnectionImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideGetCharacters(dataSource: MarvelRepositoryImpl): GetCharacters = GetCharacters(dataSource)

    @Provides
    fun provideGetComicsByCharactersId(dataSource: MarvelRepositoryImpl): GetComicsFromCharactersId =
        GetComicsFromCharactersId(dataSource)

    @Provides
    fun provideMarvelRepositoryImpl(
        api: MarvelServices,
        checkInternet: CheckInternetConnectionImpl
    ): MarvelRepositoryImpl = MarvelRepositoryImpl(api, checkInternet)
}


