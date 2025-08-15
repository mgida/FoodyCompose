package com.example.foody.feature_recipe.presentation.navigation

import kotlinx.serialization.Serializable


sealed class Screen() {
    @Serializable
    data object HomeRecipes : Screen()

    @Serializable
    data class SearchRecipes(
        val recipeCuisine: String? = null
    ) : Screen()

    @Serializable
    data class RecipeDetails(
        val recipeId: Int? = null
    ) : Screen()

    @Serializable
    data object FavouriteRecipes : Screen()
}