package com.example.foody.feature_recipe.presentation.fav_screen.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foody.R
import com.example.foody.feature_recipe.presentation.common.EmptyResult
import com.example.foody.feature_recipe.presentation.common.ErrorState
import com.example.foody.feature_recipe.presentation.fav_screen.FavouriteRecipesEvent
import com.example.foody.feature_recipe.presentation.fav_screen.viewmodel.FavouriteRecipesViewModel
import com.example.foody.feature_recipe.presentation.search_recipes_screen.components.RecipesSearchResult
import com.example.foody.feature_recipe.presentation.search_recipes_screen.components.SearchRecipesShimmerItems
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FavouriteRecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouriteRecipesViewModel = hiltViewModel(),
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedContentScope,
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val recipeSavedMessage = stringResource(id = R.string.recipe_saved_to_favourites)
    val recipeDeletedMessage = stringResource(id = R.string.recipe_removed_from_favourites)
    val actionLabel = stringResource(R.string.undo)

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(FavouriteRecipesEvent.GetRecipes)
    }

    LaunchedEffect(key1 = viewModel.eventFlow) {
        viewModel.eventFlow.collectLatest { value: FavouriteRecipesViewModel.UiEvent ->
            when (value) {
                is FavouriteRecipesViewModel.UiEvent.DeleteRecipe -> {
                    Timber.i("Foody.. recipe deleted")
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = recipeDeletedMessage,
                        actionLabel = actionLabel
                    )

                    when (snackbarResult) {
                        SnackbarResult.ActionPerformed -> {
                            viewModel.onEvent(FavouriteRecipesEvent.SaveRecipe(value.recipeModel))
                        }

                        SnackbarResult.Dismissed -> Unit
                    }
                }

                is FavouriteRecipesViewModel.UiEvent.SaveRecipe -> {
                    Timber.i("Foody.. recipe saved")
                    snackbarHostState.showSnackbar(
                        message = recipeSavedMessage
                    )
                }
            }
        }
    }


    val favState = viewModel.favState.collectAsState().value


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { paddingValues ->
            Surface(modifier = modifier.padding(paddingValues)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 16.dp, horizontal = 8.dp)
                ) {
                    when {
                        favState.isLoading -> {
                            SearchRecipesShimmerItems()
                        }

                        favState.error.isNotBlank() -> {
                            ErrorState(
                                modifier = Modifier.fillMaxSize(),
                                errorMsg = favState.error
                            )
                        }

                        favState.recipes.isEmpty() -> {
                            EmptyResult(
                                modifier = Modifier.fillMaxSize(),
                                msg = stringResource(R.string.nothing_found)
                            )
                        }

                        else -> {
                            RecipesSearchResult(
                                favState.recipes,
                                sharedTransitionScope = sharedTransitionScope,
                                animatedVisibilityScope = animatedVisibilityScope,
                                onRecipeClicked = {},
                                onFavClicked = { recipeModel ->
                                    viewModel.onEvent(FavouriteRecipesEvent.DeleteRecipe(recipeModel = recipeModel))
                                }
                            )
                        }
                    }
                }
            }
        }
    )


}