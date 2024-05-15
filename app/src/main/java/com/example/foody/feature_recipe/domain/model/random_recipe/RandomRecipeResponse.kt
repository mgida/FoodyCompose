package com.example.foody.feature_recipe.domain.model.random_recipe

import com.google.gson.annotations.SerializedName

data class RandomRecipeResponse(
    @SerializedName("recipes")
    val recipes: List<Recipe>
)