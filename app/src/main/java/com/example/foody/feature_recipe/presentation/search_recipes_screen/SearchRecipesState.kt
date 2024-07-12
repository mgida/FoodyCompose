package com.example.foody.feature_recipe.presentation.search_recipes_screen

import com.example.foody.feature_recipe.domain.model.RecipeModel

data class SearchRecipesState(
    val isLoading: Boolean = false,
    val recipes: List<RecipeModel> = emptyList(),
    val error: String = ""
)