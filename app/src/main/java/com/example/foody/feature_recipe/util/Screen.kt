package com.example.foody.feature_recipe.util

private const val RECIPES = "recipes"
private const val RECIPE_DETAIL = "recipe_details"

sealed class Screen( val route: String) {
    data object Recipes : Screen(RECIPES)
    data object RecipeDetails : Screen(RECIPE_DETAIL)
}