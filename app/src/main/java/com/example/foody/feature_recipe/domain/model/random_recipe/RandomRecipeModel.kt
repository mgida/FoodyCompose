package com.example.foody.feature_recipe.domain.model.random_recipe

data class RandomRecipeModel(
    val id: Int,
    val image: String?,
    val readyInMinutes: Int? = null,
    val servings: Int? = null,
    val summary: String?,
    val title: String?
)
