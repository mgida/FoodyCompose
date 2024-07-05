package com.example.foody.feature_recipe.presentation.home_screen

import com.example.foody.feature_recipe.domain.model.random_recipe.RandomRecipeModel

data class RandomRecipesState(
    val isLoading: Boolean = false,
    val recipes: List<RandomRecipeModel> = emptyList(),
    val error: String = ""
)