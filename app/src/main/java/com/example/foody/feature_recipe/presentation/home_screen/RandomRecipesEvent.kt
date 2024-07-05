package com.example.foody.feature_recipe.presentation.home_screen

sealed class RandomRecipesEvent {
    data object GetRecipes : RandomRecipesEvent()
}