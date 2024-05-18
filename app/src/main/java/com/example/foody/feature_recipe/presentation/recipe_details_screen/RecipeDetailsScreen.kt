package com.example.foody.feature_recipe.presentation.recipe_details_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import timber.log.Timber

@Composable
fun RecipeDetailsScreen(
    modifier: Modifier = Modifier, viewModel: RecipeDetailViewModel = hiltViewModel(), recipeId: Int
) {

    val state = viewModel.state.value

    SideEffect {
        Timber.d("Foody recipeId: $recipeId")
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = state.recipeInformationModel?.title ?: "",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}