package com.example.foody.feature_recipe.util

import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val HOME_RECIPES = "home_recipes"
private const val SEARCH_RECIPES = "search_recipes"
private const val RECIPE_DETAIL = "recipe_details"
private const val FAV_RECIPES = "fav_recipes"
const val RECIPE_ID_ARG = "recipeId"
const val RECIPE_CUISINE_ARG = "recipeCuisine"

sealed class Screen(val route: String) {
    data object HomeRecipes : Screen(HOME_RECIPES)
    data object SearchRecipes : Screen(SEARCH_RECIPES) {
        val routeWithArgs = "$route?$RECIPE_CUISINE_ARG={$RECIPE_CUISINE_ARG}"

        fun createRoute(recipeCuisine: String) = "$route?$RECIPE_CUISINE_ARG=$recipeCuisine"

        val navArgument = listOf(navArgument(name = RECIPE_CUISINE_ARG) {
            type = NavType.StringType
            defaultValue = ""
        })
    }

    data object RecipeDetails : Screen(RECIPE_DETAIL) {

        val routeWithArgs = "$route?$RECIPE_ID_ARG={$RECIPE_ID_ARG}"
        fun createRoute(recipeId: Int) = "$route?$RECIPE_ID_ARG=$recipeId"

        val navArgument = listOf(navArgument(name = RECIPE_ID_ARG) {
            type = NavType.IntType
            defaultValue = -1
        })
    }


    data object FavouriteRecipes : Screen(FAV_RECIPES)
}