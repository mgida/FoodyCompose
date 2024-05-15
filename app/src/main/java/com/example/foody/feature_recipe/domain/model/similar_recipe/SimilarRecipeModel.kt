package com.example.foody.feature_recipe.domain.model.similar_recipe

data class SimilarRecipeModel(
    val id: Int,
    val imageType: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val title: String
)
