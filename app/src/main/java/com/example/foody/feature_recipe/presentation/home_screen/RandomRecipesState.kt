package com.example.foody.feature_recipe.presentation.home_screen

import com.example.foody.feature_recipe.domain.model.RecipeModel

data class RandomRecipesState(
    val isLoading: Boolean = false,
    val recipes: List<RecipeModel> = emptyList(),
    val error: String = ""
)