package com.example.foody.feature_recipe.domain.repo

import com.example.foody.feature_recipe.data.dto.SimilarRecipesResponse
import com.example.foody.feature_recipe.domain.model.random_recipe.RandomRecipeResponse
import com.example.foody.feature_recipe.domain.model.recipe_information.RecipeInformationResponse

interface RecipeRepo {
    suspend fun getRandomRecipes(apiKey: String): RandomRecipeResponse
    suspend fun getSimilarRecipes(id: Int, apiKey: String): SimilarRecipesResponse
    suspend fun getRecipeInformation(
        apiKey: String,
        recipeId: Int
    ): RecipeInformationResponse
}