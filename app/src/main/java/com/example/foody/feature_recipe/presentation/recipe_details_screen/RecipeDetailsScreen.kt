package com.example.foody.feature_recipe.presentation.recipe_details_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foody.feature_recipe.presentation.common.ErrorState
import com.example.foody.feature_recipe.presentation.recipe_details_screen.components.RecipeDetailsImage
import com.example.foody.feature_recipe.presentation.recipe_details_screen.components.RecipeDetailsServings
import com.example.foody.feature_recipe.presentation.recipe_details_screen.components.RecipeDetailsSummary
import timber.log.Timber

@Composable
fun RecipeDetailsScreen(
    modifier: Modifier = Modifier, viewModel: RecipeDetailViewModel = hiltViewModel(), recipeId: Int
) {

    val state = viewModel.state.value

    SideEffect {
        Timber.d("Foody recipeId: $recipeId")
    }

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = modifier)
            }
        }

        state.error.isNotBlank() -> {
            ErrorState(errorMsg = state.error)
        }

        else -> {
            val recipe = state.recipeInformationModel
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
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