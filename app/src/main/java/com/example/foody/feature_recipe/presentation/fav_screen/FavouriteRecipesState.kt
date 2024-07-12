package com.example.foody.feature_recipe.presentation.fav_screen

import com.example.foody.feature_recipe.domain.model.RecipeModel


data class FavouriteRecipesState(
    val isLoading: Boolean = false,
    val recipes: List<RecipeModel> = emptyList(),
    val error: String = ""
)