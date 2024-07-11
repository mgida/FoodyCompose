package com.example.foody.feature_recipe.di

import android.app.Application
import androidx.room.Room
import com.example.foody.feature_recipe.data.local.RecipeDatabase
import com.example.foody.feature_recipe.data.local.RecipeLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRecipeDatabase(app: Application): RecipeDatabase {
        return Room.databaseBuilder(
            app,
            RecipeDatabase::class.java,
            RecipeDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().
        build()
    }

    @Provides
    @Singleton
    fun provideRecipeLocalDataSource(recipeDatabase: RecipeDatabase): RecipeLocalDataSource =
        recipeDatabase.recipeLocalDataSource

}