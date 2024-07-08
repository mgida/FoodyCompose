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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foody.feature_recipe.presentation.home_screen.viewmodel.HomeScreenViewModel
import com.example.foody.ui.theme.softWhite


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {

    val state = viewModel.state.collectAsState().value

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
                )
            }
            item {
                CuisinePager(modifier = modifier.fillMaxSize()) { cuisine ->
                    onNavigate(cuisine)
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        when {
            state.recipes.isNotEmpty() -> {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, bottom = 16.dp),
                    text = "Recipe of the week",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                RandomRecipes(
                    modifier = Modifier
                        .fillMaxWidth(),
                    recipes = state.recipes
                )
                Spacer(modifier = Modifier.height(16.dp))

            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
