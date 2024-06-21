package com.example.foody.feature_recipe.presentation.recipes_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foody.feature_recipe.presentation.common.ErrorState
import com.example.foody.feature_recipe.presentation.recipes_screen.components.CustomBasicTextField
import com.example.foody.feature_recipe.presentation.recipes_screen.components.RecipesSearchResult
import com.example.foody.feature_recipe.presentation.recipes_screen.components.RecipesShimmerItems


@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: RecipesViewModel = hiltViewModel(),
    onItemClick: (id: Int) -> Unit
) {

    var value by rememberSaveable {
        mutableStateOf("")
    }

    val state = viewModel.state.collectAsState().value

    Surface(modifier = modifier.background(color = MaterialTheme.colorScheme.background)) {

        Column {
            CustomBasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = value,
                onValueChange = {
                    value = it
                },
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                hint = "Search recipes..",
            )

            when {
                state.isLoading -> {
                    RecipesShimmerItems()
                }

                state.error.isNotBlank() -> {
                    ErrorState(state.error)
                }

                else -> {
                    RecipesSearchResult(state.recipes, onItemClick)
                }
            }
        }
    }
}





