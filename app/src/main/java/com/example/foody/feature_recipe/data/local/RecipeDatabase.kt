package com.example.foody.feature_recipe.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foody.feature_recipe.domain.model.RecipeModel


@Database(
    entities = [RecipeModel::class],
    version = 1
)
abstract class RecipeDatabase : RoomDatabase() {

    abstract val recipeLocalDataSource: RecipeLocalDataSource

    companion object {
        const val DATABASE_NAME = "recipes_db"
    }
}