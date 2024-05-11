package com.example.foody.feature_recipe.di

import com.example.foody.feature_recipe.data.remote.RecipeRemoteDataSource
import com.example.foody.feature_recipe.data.repo.RecipeRepoImpl
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RandomRecipesModule {

    @Provides
    @Singleton
    fun provideRecipeRepo(
        recipeRemoteDataSource: RecipeRemoteDataSource
    ): RecipeRepo =
        RecipeRepoImpl(
            recipeRemoteDataSource = recipeRemoteDataSource
        )
}