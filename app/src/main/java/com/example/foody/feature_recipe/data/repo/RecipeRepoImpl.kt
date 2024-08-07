package com.example.foody.feature_recipe.data.repo

import com.example.foody.feature_recipe.data.dto.random_recipe.RandomRecipeResponse
import com.example.foody.feature_recipe.data.dto.recipe_information.RecipeInformationResponse
import com.example.foody.feature_recipe.data.dto.search_recipes.SearchRecipesResponse
import com.example.foody.feature_recipe.data.dto.similar_recipe.SimilarRecipesResponse
import com.example.foody.feature_recipe.data.local.RecipeLocalDataSource
import com.example.foody.feature_recipe.data.remote.RecipeRemoteDataSource
import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import kotlinx.coroutines.flow.Flow

class RecipeRepoImpl(
    private val recipeRemoteDataSource: RecipeRemoteDataSource,
    private val recipeLocalDataSource: RecipeLocalDataSource,
) : RecipeRepo {

    override suspend fun searchRecipes(
        apiKey: String,
        cuisine: String,
        query: String
    ): SearchRecipesResponse =
        recipeRemoteDataSource.searchRecipes(apiKey = apiKey, cuisine = cuisine, query = query)

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

    override fun getFavRecipes(): Flow<List<RecipeModel>> =
        recipeLocalDataSource.getFavRecipes()


    override suspend fun getRecipeById(id: Int): RecipeModel? =
        recipeLocalDataSource.getRecipeById(id)

    override suspend fun insertRecipe(recipeModel: RecipeModel) =
        recipeLocalDataSource.insertRecipe(recipeModel)

    override suspend fun deleteRecipe(recipeModel: RecipeModel) =
        recipeLocalDataSource.deleteRecipe(recipeModel)

    override suspend fun insertRecipes(recipes: List<RecipeModel>) {
        recipeLocalDataSource.insertRecipes(recipes)
    }

    override suspend fun deleteRecipes(recipes: List<RecipeModel>) {
        recipeLocalDataSource.deleteRecipes(recipes)
    }
}