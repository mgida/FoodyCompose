package com.example.foody.feature_recipe.domain.repo

import com.example.foody.feature_recipe.domain.model.random_recipe.RandomRecipeResponse
import com.example.foody.feature_recipe.domain.model.recipe_information.RecipeInformationResponse
import com.example.foody.feature_recipe.util.Resource

interface RecipeRepo {
    suspend fun getRandomRecipes(apiKey: String): Resource<RandomRecipeResponse?>
    suspend fun getRecipeInformation(
        apiKey: String,
        recipeId: Int
    ): Resource<RecipeInformationResponse?>
}