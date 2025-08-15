package com.example.foody.feature_recipe.presentation.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.foody.feature_recipe.presentation.fav_screen.components.FavouriteRecipesScreen
import com.example.foody.feature_recipe.presentation.home_screen.components.HomeScreen
import com.example.foody.feature_recipe.presentation.recipe_details_screen.RecipeDetailsScreen
import com.example.foody.feature_recipe.presentation.search_recipes_screen.SearchRecipesScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RecipesNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    SharedTransitionLayout {

        NavHost(
            navController = navController,
            startDestination = Screen.HomeRecipes
        )
        {

            composable<Screen.HomeRecipes> {
                HomeScreen(modifier = modifier, onNavigate = { cuisine ->
                    navController.navigate(
                        Screen.SearchRecipes(
                            recipeCuisine = cuisine

                        )
                    )
                }, onNavigateToFav = {
                    navigateToFavouritesScreen(navController)
                }) { recipeId ->
                    navigateToDetailsScreen(navController, recipeId)
                }
            }

            composable<Screen.SearchRecipes> { backStackEntry ->
                val route = backStackEntry.toRoute<Screen.SearchRecipes>()
                SearchRecipesScreen(
                    modifier = modifier,
                    cuisine = route.recipeCuisine.orEmpty(),
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this,
                    onBackClicked = { navController.popBackStack() },
                    onNavigateToFav = {
                        navigateToFavouritesScreen(navController)
                    }
                ) { recipeId ->
                    navigateToDetailsScreen(navController, recipeId)
                }
            }



            composable<Screen.RecipeDetails> { backStackEntry ->

                val route = backStackEntry.toRoute<Screen.RecipeDetails>()
                RecipeDetailsScreen(
                    modifier = modifier,
                    recipeId = route.recipeId ?: -1,
                    onBackClicked = { navController.popBackStack() },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this,
                )
            }

            composable<Screen.FavouriteRecipes> {
                FavouriteRecipesScreen(
                    modifier = modifier,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this,
                    onBackClick = { navController.popBackStack() },
                    onRecipeClicked = { recipeId ->
                        navigateToDetailsScreen(navController, recipeId)
                    }
                )
            }
        }
    }
}

fun navigateToFavouritesScreen(navController: NavHostController) =
    navController.navigateWithSavingState(Screen.FavouriteRecipes)

fun navigateToDetailsScreen(
    navController: NavHostController,
    recipeId: Int,
) {
    navController.navigateWithSavingState(
        Screen.RecipeDetails(recipeId = recipeId),
    )
}
