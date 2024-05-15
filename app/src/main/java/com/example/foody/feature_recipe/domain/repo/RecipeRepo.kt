package com.example.foody.feature_recipe.domain.repo

import com.example.foody.feature_recipe.util.Resource
import com.example.foody.feature_recipe.domain.model.random_recipe.RandomRecipeResponse

interface RecipeRepo {
    suspend fun getRandomRecipes(apiKey: String): Resource<RandomRecipeResponse?>
}