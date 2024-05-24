package com.example.foody.feature_recipe.presentation.recipe_details_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foody.feature_recipe.presentation.common.ErrorState
import com.example.foody.feature_recipe.presentation.recipe_details_screen.components.RecipeDetailsImage
import com.example.foody.feature_recipe.presentation.recipe_details_screen.components.RecipeDetailsServings
import com.example.foody.feature_recipe.presentation.recipe_details_screen.components.RecipeDetailsSummary
import com.example.foody.feature_recipe.presentation.recipe_details_screen.components.SimilarRecipeItem
import timber.log.Timber

@Composable
fun RecipeDetailsScreen(
    modifier: Modifier = Modifier, viewModel: RecipeDetailViewModel = hiltViewModel(), recipeId: Int
) {

    val recipeInformationState = viewModel.recipeInformationState.value
    val similarRecipesState = viewModel.similarRecipesState.value

    SideEffect {
        Timber.d("Foody recipeId: $recipeId")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        RecipeContentSection(recipeInformationState, modifier)
        SimilarRecipesSection(similarRecipesState, modifier)
    }
}

@Composable
fun SimilarRecipesSection(similarRecipesState: SimilarRecipesState, modifier: Modifier) {
    when {
        similarRecipesState.isLoading -> {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = modifier)
            }
        }

        similarRecipesState.error.isNotBlank() -> {
            ErrorState(errorMsg = similarRecipesState.error)
        }

        else -> {
            val similarRecipes = similarRecipesState.similarRecipes
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(similarRecipes) { similarRecipe ->
                    SimilarRecipeItem(similarRecipe)
                }
            }
        }
    }
}

@Composable
private fun RecipeContentSection(
    recipeInformationState: RecipeDetailState,
    modifier: Modifier = Modifier
) {
    when {
        recipeInformationState.isLoading -> {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = modifier)
            }
        }

        recipeInformationState.error.isNotBlank() -> {
            ErrorState(errorMsg = recipeInformationState.error)
        }

        else -> {
            val recipe = recipeInformationState.recipeInformationModel
            LazyColumn(
                modifier = modifier.height(400.dp)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                item { RecipeDetailsImage(recipe?.image) }
                item {
                    RecipeDetailsSummary(
                        recipe?.title ?: "",
                        recipe?.summary ?: ""
                    )
                }
                item {
                    RecipeDetailsServings(
                        recipe?.servings,
                        recipe?.readyInMinutes
                    )
                }
            }
        }
    }
}




