package com.example.foody.feature_recipe.presentation.recipes_screen

sealed class RandomRecipesEvent {
    data object GetRecipes : RandomRecipesEvent()
}