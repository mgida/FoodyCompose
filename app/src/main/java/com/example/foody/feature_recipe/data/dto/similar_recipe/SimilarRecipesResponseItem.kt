package com.example.foody.feature_recipe.data.dto.similar_recipe

data class SimilarRecipesResponseItem(
    val id: Int,
    val imageType: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val title: String
)