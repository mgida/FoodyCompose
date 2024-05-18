package com.example.foody.feature_recipe.util

import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val RECIPES = "recipes"
private const val RECIPE_DETAIL = "recipe_details"
const val RECIPE_ID_ARG = "recipeId"

sealed class Screen(val route: String) {
    data object Recipes : Screen(RECIPES)
    data object RecipeDetails : Screen(RECIPE_DETAIL) {

        val routeWithArgs = "$route?$RECIPE_ID_ARG={$RECIPE_ID_ARG}"
        fun createRoute(recipeId: Int) = "$route?$RECIPE_ID_ARG=$recipeId"

        val navArgument = listOf(navArgument(name = RECIPE_ID_ARG) {
            type = NavType.IntType
            defaultValue = -1
        })
    }
}