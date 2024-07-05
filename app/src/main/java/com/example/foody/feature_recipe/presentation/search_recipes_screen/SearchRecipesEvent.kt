package com.example.foody.feature_recipe.presentation.search_recipes_screen

sealed class SearchRecipesEvent {
    data class GetRecipes(val query: String) : SearchRecipesEvent()
}