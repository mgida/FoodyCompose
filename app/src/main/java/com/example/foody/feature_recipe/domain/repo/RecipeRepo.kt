package com.example.foody.feature_recipe.domain.repo

import com.example.foody.feature_recipe.data.dto.random_recipe.RandomRecipeResponse
import com.example.foody.feature_recipe.data.dto.recipe_information.RecipeInformationResponse
import com.example.foody.feature_recipe.data.dto.search_recipes.SearchRecipesResponse
import com.example.foody.feature_recipe.data.dto.similar_recipe.SimilarRecipesResponse
import com.example.foody.feature_recipe.domain.model.RecipeModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepo {

    suspend fun searchRecipes(
        apiKey: String,
        cuisine: String,
        query: String,
    ): SearchRecipesResponse

    suspend fun getRandomRecipes(apiKey: String): RandomRecipeResponse
    suspend fun getSimilarRecipes(id: Int, apiKey: String): SimilarRecipesResponse
    suspend fun getRecipeInformation(
        apiKey: String,
        recipeId: Int
    ): RecipeInformationResponse


    fun getFavRecipes(): Flow<List<RecipeModel>>
    suspend fun getRecipeById(id: Int): RecipeModel?
    suspend fun insertRecipe(recipeModel: RecipeModel)
    suspend fun deleteRecipe(recipeModel: RecipeModel)
}