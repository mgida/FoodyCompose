package com.example.foody.feature_recipe.domain.repo

import com.example.foody.feature_recipe.data.dto.similar_recipe.SimilarRecipesResponse
import com.example.foody.feature_recipe.data.dto.random_recipe.RandomRecipeResponse
import com.example.foody.feature_recipe.data.dto.recipe_information.RecipeInformationResponse

interface RecipeRepo {
    suspend fun getRandomRecipes(apiKey: String): RandomRecipeResponse
    suspend fun getSimilarRecipes(id: Int, apiKey: String): SimilarRecipesResponse
    suspend fun getRecipeInformation(
        apiKey: String,
        recipeId: Int
    ): RecipeInformationResponse
}