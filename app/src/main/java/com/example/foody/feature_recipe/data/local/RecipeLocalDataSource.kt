package com.example.foody.feature_recipe.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foody.feature_recipe.domain.model.search_recipes.SearchRecipesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeModel: SearchRecipesModel)

    @Delete
    suspend fun deleteRecipe(recipeModel: SearchRecipesModel)

    @Query("SELECT * FROM SearchRecipesModel")
    fun getRecipes(): Flow<List<SearchRecipesModel>>

    @Query("SELECT * FROM SearchRecipesModel WHERE id = :id")
    suspend fun getRecipeById(id: Int): SearchRecipesModel?

}