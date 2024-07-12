package com.example.foody.feature_recipe.presentation.fav_screen

sealed class FavouriteRecipesEvent {
    data object GetRecipes : FavouriteRecipesEvent()
}