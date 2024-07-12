package com.example.foody.feature_recipe.presentation.fav_screen.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FavouriteRecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouriteRecipesViewModel = hiltViewModel(),
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedContentScope,
) {

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(FavouriteRecipesEvent.GetRecipes)
    }

    val favState = viewModel.favState.collectAsState().value


    Surface(modifier = modifier) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 8.dp)) {
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
                        onFavClicked = {}
                    )
                }
            }
        }
    }

}