package com.example.foody.feature_recipe.presentation.recipe_details_screen

import com.example.foody.feature_recipe.domain.model.similar_recipe.SimilarRecipeModel

data class SimilarRecipesState(
    val isLoading: Boolean = false,
    val similarRecipes: List<SimilarRecipeModel> = listOf(),
    val error: String = ""
)
