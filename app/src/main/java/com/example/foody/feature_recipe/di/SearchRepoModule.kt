package com.example.foody.feature_recipe.di

import android.app.Application
import android.content.Context
import com.example.foody.feature_recipe.data.repo.SearchRepositoryImpl
import com.example.foody.feature_recipe.domain.repo.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchRepoModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideSearchRepo(
        context: Context
    ): SearchRepository =
        SearchRepositoryImpl(
            context = context
        )
}