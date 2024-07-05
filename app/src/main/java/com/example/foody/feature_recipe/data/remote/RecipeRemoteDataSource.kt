package com.example.foody.feature_recipe.data.remote

import com.example.foody.feature_recipe.data.dto.random_recipe.RandomRecipeResponse
import com.example.foody.feature_recipe.data.dto.recipe_information.RecipeInformationResponse
import com.example.foody.feature_recipe.data.dto.search_recipes.SearchRecipesResponse
import com.example.foody.feature_recipe.data.dto.similar_recipe.SimilarRecipesResponse
import com.example.foody.feature_recipe.util.DEFAULT_QUERY_SEARCH
import com.example.foody.feature_recipe.util.DEFAULT_RECIPES_NUMBER
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeRemoteDataSource {

    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("apiKey") apiKey: String,
        @Query("cuisine") cuisine: String,
        @Query("number") number: Int = DEFAULT_RECIPES_NUMBER,
        @Query("query") query: String = DEFAULT_QUERY_SEARCH,
        @Query("addRecipeInformation") addRecipeInformation: Boolean = true
    ): SearchRecipesResponse

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
        @Path("id") recipeId: Int,
        @Query("apiKey") apiKey: String,
    ): RecipeInformationResponse
}






