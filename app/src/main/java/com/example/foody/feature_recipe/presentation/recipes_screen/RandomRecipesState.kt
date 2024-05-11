package com.example.foody.feature_recipe.presentation.recipes_screen

import com.example.foody.feature_recipe.domain.model.random_recipe.Recipe

data class RandomRecipesState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val error: String = "",

    )

enum class RecipesCuisine {
    Mexican,
    Italian,
    Japanese,
    French
}
