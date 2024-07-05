package com.example.foody.feature_recipe.presentation.search_recipes_screen

import com.example.foody.feature_recipe.domain.model.search_recipes.SearchRecipesModel

data class SearchRecipesState(
    val isLoading: Boolean = false,
    val recipes: List<SearchRecipesModel> = emptyList(),
    val error: String = ""
)