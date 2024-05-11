package com.example.foody.feature_recipe.data.repo

import com.example.foody.feature_recipe.data.dto.SimilarRecipesResponse
import com.example.foody.feature_recipe.data.remote.RecipeRemoteDataSource
import com.example.foody.feature_recipe.domain.model.random_recipe.RandomRecipeResponse
import com.example.foody.feature_recipe.domain.model.recipe_information.RecipeInformationResponse
import com.example.foody.feature_recipe.domain.repo.RecipeRepo

class RecipeRepoImpl(
    private val recipeRemoteDataSource: RecipeRemoteDataSource
) : RecipeRepo {
    override suspend fun getRandomRecipes(apiKey: String): RandomRecipeResponse =
        recipeRemoteDataSource.getRandomRecipes(apiKey)

    override suspend fun getSimilarRecipes(
        id: Int,
        apiKey: String
    ): SimilarRecipesResponse = recipeRemoteDataSource.getSimilarRecipes(id, apiKey)

    override suspend fun getRecipeInformation(
        apiKey: String,
        recipeId: Int
    ): RecipeInformationResponse =
        recipeRemoteDataSource.getRecipeInformation(apiKey = apiKey, recipeId = recipeId)
}