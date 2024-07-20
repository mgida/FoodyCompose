package com.example.foody.feature_recipe.presentation.fav_screen.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foody.R
import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.presentation.common.CustomTopBar
import com.example.foody.feature_recipe.presentation.common.EmptyResult
import com.example.foody.feature_recipe.presentation.common.ErrorState
import com.example.foody.feature_recipe.presentation.fav_screen.FavouriteRecipesEvent
import com.example.foody.feature_recipe.presentation.fav_screen.viewmodel.FavouriteRecipesViewModel
import com.example.foody.feature_recipe.presentation.search_recipes_screen.components.RecipesSearchResult
import com.example.foody.feature_recipe.presentation.search_recipes_screen.components.SearchRecipesShimmerItems
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FavouriteRecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouriteRecipesViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onRecipeClicked: (id: Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedContentScope,
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val selectedRecipes = remember { mutableStateListOf<RecipeModel>() }

    val recipeSavedMessage = stringResource(id = R.string.recipe_saved_to_favourites)
    val recipeDeletedMessage = stringResource(id = R.string.recipe_removed_from_favourites)
    val deletedRecipesMessage = stringResource(id = R.string.recipes_deleted)
    val savedRecipesMessage = stringResource(id = R.string.recipes_saved)
    val actionLabel = stringResource(R.string.undo)

    LaunchedEffect(Unit) {
        viewModel.onEvent(FavouriteRecipesEvent.GetRecipes)
    }

    LaunchedEffect(key1 = viewModel.eventFlow) {
        viewModel.eventFlow.collectLatest { value: FavouriteRecipesViewModel.UiEvent ->
            when (value) {
                is FavouriteRecipesViewModel.UiEvent.DeleteRecipe -> {
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
                    snackbarHostState.showSnackbar(
                        message = recipeSavedMessage
                    )
                }

                is FavouriteRecipesViewModel.UiEvent.MultipleRecipesSaved -> {
                    snackbarHostState.showSnackbar(
                        message = savedRecipesMessage
                    )
                }

                is FavouriteRecipesViewModel.UiEvent.MultipleRecipesDeleted -> {
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = deletedRecipesMessage,
                        actionLabel = actionLabel
                    )
                    if (snackbarResult == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(FavouriteRecipesEvent.SaveRecipes(value.recipes))
                    }
                }
            }
        }
    }


    val favState = viewModel.favState.collectAsState().value

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CustomTopBar(
                title = stringResource(id = R.string.favourite_recipes),
                icon = Icons.AutoMirrored.Default.ArrowBack
            ) {
                onBackClick.invoke()
            }
        },
        floatingActionButton = {
            if (selectedRecipes.isNotEmpty()) {
                FloatingActionButton(onClick = {
                    viewModel.onEvent(
                        FavouriteRecipesEvent.DeleteRecipes(
                            selectedRecipes.toList()
                        )
                    )
                    selectedRecipes.clear()
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
        },
        content = { paddingValues ->
            Surface(modifier = modifier.padding(paddingValues)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
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
                                selectedRecipes = selectedRecipes,
                                onRecipeClicked = { recipeId ->
                                    onRecipeClicked.invoke(recipeId)
                                },
                                onFavClicked = { recipeModel ->
                                    viewModel.onEvent(FavouriteRecipesEvent.DeleteRecipe(recipeModel = recipeModel))
                                },
                                onShareClicked = {sourceUrl ->
                                    viewModel.onEvent(FavouriteRecipesEvent.ShareRecipe(sourceUrl = sourceUrl))
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}