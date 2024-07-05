package com.example.foody.feature_recipe.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foody.feature_recipe.presentation.recipe_details_screen.RecipeDetailsScreen
import com.example.foody.feature_recipe.presentation.search_recipes_screen.RecipesScreen
import com.example.foody.feature_recipe.util.RECIPE_ID_ARG
import com.example.foody.feature_recipe.util.Screen


@Composable
fun RecipesNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Recipes.route
    )
    {

        composable(route = Screen.Recipes.route) {
            RecipesScreen(modifier = modifier) { recipeId ->
                navController.navigate(Screen.RecipeDetails.createRoute(recipeId = recipeId))
            }
        }

        composable(
            route = Screen.RecipeDetails.routeWithArgs,
            arguments = Screen.RecipeDetails.navArgument
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt(RECIPE_ID_ARG) ?: -1
            RecipeDetailsScreen(modifier = modifier, recipeId = recipeId)
        }
    }
}