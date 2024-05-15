package com.example.foody.feature_recipe.presentation.recipes_screen

import com.example.foody.feature_recipe.domain.model.random_recipe.Recipe

data class RandomRecipesState(
    val recipes: List<Recipe> = emptyList()
)
