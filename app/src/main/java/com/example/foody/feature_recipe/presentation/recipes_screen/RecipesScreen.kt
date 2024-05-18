package com.example.foody.feature_recipe.presentation.recipes_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foody.feature_recipe.presentation.recipes_screen.components.RecipeItem


@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: RecipesViewModel = hiltViewModel(),
    onItemClick: (id: Int) -> Unit
) {
    val state = viewModel.state.value

    Surface(modifier = modifier.background(color = MaterialTheme.colorScheme.background)) {

        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error.isNotBlank() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            else -> {
                LazyColumn {

                    items(state.recipes) {
                        RecipeItem(
                            modifier = Modifier.clickable {
                                it.id?.let { it1 -> onItemClick.invoke(it1) }
                            },
                            it.title ?: "",
                            it.summary ?: "",
                            it.image
                        )
                    }
                }
            }
        }
    }
}