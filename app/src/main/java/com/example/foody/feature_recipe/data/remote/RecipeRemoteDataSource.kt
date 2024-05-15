package com.example.foody.feature_recipe.data.remote

import com.example.foody.feature_recipe.data.dto.similar_recipe.SimilarRecipesResponse
import com.example.foody.feature_recipe.data.dto.random_recipe.RandomRecipeResponse
import com.example.foody.feature_recipe.data.dto.recipe_information.RecipeInformationResponse
import com.example.foody.feature_recipe.util.DEFAULT_RECIPES_NUMBER
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeRemoteDataSource {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int = DEFAULT_RECIPES_NUMBER,
    ): RandomRecipeResponse

    @GET("recipes/{id}/similar")
    suspend fun getSimilarRecipes(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int = DEFAULT_RECIPES_NUMBER,
    ): SimilarRecipesResponse


    @GET("recipes/{id}/information")
    suspend fun getRecipeInformation(
        @Query("apiKey") apiKey: String,
        @Path("id") recipeId: Int
    ): RecipeInformationResponse
}






