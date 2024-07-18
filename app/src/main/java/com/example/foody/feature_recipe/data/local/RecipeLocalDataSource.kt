package com.example.foody.feature_recipe.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foody.feature_recipe.domain.model.RecipeModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeModel: RecipeModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeModel>)

    @Delete
    suspend fun deleteRecipe(recipeModel: RecipeModel)

    @Delete
    suspend fun deleteRecipes(recipes: List<RecipeModel>)

    @Query("SELECT * FROM RecipeModel")
    fun getFavRecipes(): Flow<List<RecipeModel>>

    @Query("SELECT * FROM RecipeModel WHERE id = :id")
    suspend fun getRecipeById(id: Int): RecipeModel?

}