package com.example.foody.feature_recipe.data.dto.random_recipe

import com.google.gson.annotations.SerializedName

data class RandomRecipeResponse(
    @SerializedName("recipes")
    val recipes: List<RecipeResponse>
)