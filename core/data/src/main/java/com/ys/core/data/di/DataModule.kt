package com.ys.core.data.di

import com.ys.core.data.repository.ArticlesRepository
import com.ys.core.data.repository.ArticlesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun provideArticlesRepository(articlesRepositoryImpl: ArticlesRepositoryImpl): ArticlesRepository
}