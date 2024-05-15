package com.example.foody.feature_recipe.data.remote

import com.example.foody.feature_recipe.domain.model.random_recipe.RandomRecipeResponse
import com.example.foody.feature_recipe.util.RANDOM_RECIPES_NUMBER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeRemoteDataSource {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int = RANDOM_RECIPES_NUMBER,
    ): Response<RandomRecipeResponse>
}






