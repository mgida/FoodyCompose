package com.example.foody.feature_recipe.presentation.recipes_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foody.feature_recipe.presentation.RecipeItem


@Composable
fun RecipesScreen(modifier: Modifier = Modifier, viewModel: RecipesViewModel = hiltViewModel()) {
    val state = viewModel.state.value

    Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
        LazyColumn {
            items(state.recipes) {
                RecipeItem(modifier = Modifier, it.title, it.summary, it.image)
            }
        }
    }
}