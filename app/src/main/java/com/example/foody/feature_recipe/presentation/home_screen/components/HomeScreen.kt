package com.example.foody.feature_recipe.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foody.R
import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.presentation.common.EmptyResult
import com.example.foody.feature_recipe.presentation.common.ErrorState
import com.example.foody.feature_recipe.presentation.home_screen.RandomRecipesState
import com.example.foody.feature_recipe.presentation.home_screen.viewmodel.HomeScreenViewModel
import com.example.foody.feature_recipe.util.ThemePreviews
import com.example.foody.ui.theme.FoodyTheme
import com.example.foody.ui.theme.softWhite


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit,
    onNavigateToFav: () -> Unit,
    onRecipeItemClicked: (recipeId: Int) -> Unit,

    ) {

    val state = viewModel.state.collectAsState().value
    HomeContent(modifier, onNavigate, onNavigateToFav, onRecipeItemClicked, state)
}

@Composable
fun HomeContent(
    modifier: Modifier,
    onNavigate: (String) -> Unit,
    onNavigateToFav: () -> Unit,
    onRecipeItemClicked: (recipeId: Int) -> Unit,
    state: RandomRecipesState,

    ) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(softWhite)
    ) {
        LazyColumn(
            modifier = modifier
                .height(630.dp)
                .fillMaxWidth()

        ) {
            item {
                CustomAppBar(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    onNavigateToFav.invoke()
                }
            }
            item {
                CuisinePager(modifier = modifier.fillMaxSize()) { cuisine ->
                    onNavigate(cuisine)
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        when {
            state.isLoading -> {
                RandomRecipesShimmerItems(modifier = Modifier.fillMaxWidth())
            }

            state.error.isNotBlank() -> {
                ErrorState(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag(stringResource(id = R.string.error_message)),
                    errorMsg = state.error
                )
            }

            state.recipes.isEmpty() -> {
                EmptyResult(
                    modifier = Modifier.fillMaxWidth(),
                    msg = stringResource(id = R.string.nothing_found)
                )
            }

            else -> {
                RecipesResult(state.recipes, onRecipeItemClicked)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun RecipesResult(
    recipes: List<RecipeModel>,
    onRecipeItemClicked: (recipeId: Int) -> Unit
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, bottom = 16.dp),
        text = stringResource(R.string.recipe_of_the_week),
        style = MaterialTheme.typography.titleMedium,
        color = Color.Black
    )
    RandomRecipes(
        modifier = Modifier
            .fillMaxWidth(),
        recipes = recipes,
        onRecipeItemClicked
    )
    Spacer(modifier = Modifier.height(16.dp))
}


@ThemePreviews
@Composable
private fun HomeContentPreview() {
    FoodyTheme {
        HomeContent(
            modifier = Modifier.fillMaxWidth(),
            onNavigate = {},
            onNavigateToFav = {},
            onRecipeItemClicked = {},
            state = RandomRecipesState(isLoading = true)
        )
    }
}