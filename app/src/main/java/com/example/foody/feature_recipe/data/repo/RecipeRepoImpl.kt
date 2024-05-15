package com.example.foody.feature_recipe.data.repo

import com.example.foody.feature_recipe.data.remote.RecipeRemoteDataSource
import com.example.foody.feature_recipe.domain.model.random_recipe.RandomRecipeResponse
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import com.example.foody.feature_recipe.util.Resource

class RecipeRepoImpl(
    private val recipeRemoteDataSource: RecipeRemoteDataSource
) : RecipeRepo {
    override suspend fun getRandomRecipes(apiKey: String): Resource<RandomRecipeResponse?> {
        val randomRecipes = recipeRemoteDataSource.getRandomRecipes(apiKey)

        return if (randomRecipes.isSuccessful) {
            val data = randomRecipes.body()
            Resource.Success(data)
        } else {
            Resource.Error(message = randomRecipes.message(), null)
        }
    }
}