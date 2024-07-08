package com.example.foody.feature_recipe.presentation.search_recipes_screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foody.feature_recipe.presentation.common.EmptyResult
import com.example.foody.feature_recipe.presentation.common.ErrorState
import com.example.foody.feature_recipe.presentation.search_recipes_screen.components.CustomBasicTextField
import com.example.foody.feature_recipe.presentation.search_recipes_screen.components.RecipesSearchResult
import com.example.foody.feature_recipe.presentation.search_recipes_screen.components.RecipesShimmerItems
import com.example.foody.feature_recipe.presentation.search_recipes_screen.viewmodel.RecipesViewModel
import com.example.foody.ui.theme.coolGray
import com.example.foody.ui.theme.softWhite
import timber.log.Timber


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SearchRecipesScreen(
    modifier: Modifier = Modifier,
    cuisine: String,
    viewModel: RecipesViewModel = hiltViewModel(),
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedContentScope,
    onItemClick: (id: Int) -> Unit
) {

    SideEffect {
        Timber.d("Foody recipeCuisine: $cuisine")
    }

    var value by rememberSaveable {
        mutableStateOf("")
    }

    val searchState = viewModel.searchState.collectAsState().value

    Surface(modifier = modifier.background(color = softWhite)) {

        Column {
            CustomBasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = value,
                onValueChange = {
                    value = it
                    if (it.length > 5)
                        viewModel.onEvent(SearchRecipesEvent.GetRecipes(it))
                },
                backgroundColor = Color.White,
                textStyle = MaterialTheme.typography.bodySmall.copy(color = coolGray),
                hint = "Search recipes..",
            )

            when {
                searchState.isLoading -> {
                    RecipesShimmerItems()
                }

                searchState.error.isNotBlank() -> {
                    ErrorState(searchState.error)
                }

                searchState.recipes.isEmpty() -> {
                    EmptyResult(msg = "Nothing Found..")
                }

                else -> {
                    RecipesSearchResult(
                        searchState.recipes,
                        sharedTransitionScope = sharedTransitionScope,
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick
                    )
                }
            }
        }
    }
}





