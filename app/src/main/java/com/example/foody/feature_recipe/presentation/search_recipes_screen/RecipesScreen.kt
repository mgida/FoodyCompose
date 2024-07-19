package com.example.foody.feature_recipe.presentation.search_recipes_screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foody.R
import com.example.foody.feature_recipe.presentation.common.CustomTopBar
import com.example.foody.feature_recipe.presentation.common.EmptyResult
import com.example.foody.feature_recipe.presentation.common.ErrorState
import com.example.foody.feature_recipe.presentation.search_recipes_screen.components.CustomBasicTextField
import com.example.foody.feature_recipe.presentation.search_recipes_screen.components.RecipesSearchResult
import com.example.foody.feature_recipe.presentation.search_recipes_screen.components.SearchRecipesShimmerItems
import com.example.foody.feature_recipe.presentation.search_recipes_screen.viewmodel.RecipesViewModel
import com.example.foody.feature_recipe.util.MIN_SEARCH_LENGTH
import com.example.foody.ui.theme.coolGray
import com.example.foody.ui.theme.softWhite
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SearchRecipesScreen(
    modifier: Modifier = Modifier,
    cuisine: String,
    viewModel: RecipesViewModel = hiltViewModel(),
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedContentScope,
    onBackClicked: () -> Unit,
    onNavigateToFav: () -> Unit,
    onItemClick: (id: Int) -> Unit
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var isFocused by remember {
        mutableStateOf(false)
    }

    val searchState = viewModel.searchState.collectAsState().value
    val recentSearches = viewModel.recentSearches.collectAsState().value

    val snackbarHostState = remember { SnackbarHostState() }
    val recipeSavedMessage = stringResource(id = R.string.recipe_saved_to_favourites)
    val recipeDeletedMessage = stringResource(id = R.string.recipe_removed_from_favourites)
    val actionLabel = stringResource(id = R.string.view_recipe)

    SideEffect {
        Timber.d("Foody recipeCuisine: $cuisine")
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { value: RecipesViewModel.UiEvent ->
            when (value) {
                is RecipesViewModel.UiEvent.SaveRecipe -> {
                    Timber.i("Foody.. recipe saved")
                    val snackbarResult =
                        snackbarHostState.showSnackbar(
                            message = recipeSavedMessage,
                            actionLabel = actionLabel
                        )
                    when (snackbarResult) {
                        SnackbarResult.ActionPerformed -> {
                            onNavigateToFav.invoke()
                        }

                        SnackbarResult.Dismissed -> Unit
                    }
                }

                RecipesViewModel.UiEvent.DeleteRecipe -> {
                    Timber.i("Foody.. recipe deleted")
                    snackbarHostState.showSnackbar(message = recipeDeletedMessage)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CustomTopBar(
                title = stringResource(R.string.food, cuisine),
                icon = Icons.AutoMirrored.Default.ArrowBack
            ) {
                onBackClicked.invoke()
            }
        },
        content = { paddingValues ->

            Surface(modifier = modifier.background(color = softWhite)) {

                Column(modifier = Modifier.padding(paddingValues)) {
                    CustomBasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, start = 8.dp, end = 8.dp)
                            .onFocusChanged {
                                isFocused = it.isFocused
                            },
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                            if (it.length > MIN_SEARCH_LENGTH) {
                                with(viewModel) {
                                    onEvent(SearchRecipesEvent.GetRecipes(it))
                                    onEvent(SearchRecipesEvent.SaveSearchQuery(it))
                                }
                            }
                        },
                        backgroundColor = Color.White,
                        textStyle = MaterialTheme.typography.bodyMedium.copy(color = coolGray),
                        hint = stringResource(R.string.search_recipes),
                        recentSearches = recentSearches
                    ) { recentSearch ->
                        searchQuery = recentSearch
                        viewModel.onEvent(SearchRecipesEvent.GetRecipes(query = recentSearch))
                    }

                    when {
                        searchState.isLoading -> {
                            SearchRecipesShimmerItems()
                        }

                        searchState.error.isNotBlank() -> {
                            ErrorState(
                                modifier = Modifier.fillMaxSize(),
                                errorMsg = searchState.error
                            )
                        }

                        searchState.recipes.isEmpty() -> {
                            EmptyResult(
                                modifier = Modifier.fillMaxSize(),
                                msg = stringResource(R.string.nothing_found)
                            )
                        }

                        else -> {
                            RecipesSearchResult(
                                searchState.recipes,
                                sharedTransitionScope = sharedTransitionScope,
                                animatedVisibilityScope = animatedVisibilityScope,
                                onRecipeClicked = onItemClick,
                                onFavClicked = { searchRecipeModel ->

                                    if (!searchRecipeModel.isFav) {
                                        viewModel.onEvent(
                                            SearchRecipesEvent.DeleteRecipe(
                                                searchRecipeModel
                                            )
                                        )
                                    } else {
                                        viewModel.onEvent(
                                            SearchRecipesEvent.SaveRecipe(
                                                searchRecipeModel
                                            )
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}