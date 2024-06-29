package com.example.foody.feature_recipe.presentation.recipe_details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foody.feature_recipe.domain.model.recipe_information.RecipeIngredientModel
import com.example.foody.feature_recipe.util.ThemePreviews


@Composable
fun RecipesIngredients(modifier: Modifier = Modifier, ingredients: List<RecipeIngredientModel>) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        //todo check fixed height
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)

    ) {
        items(ingredients) {
            RecipeIngredientItem(name = it.name, amount = it.amount)
        }
    }
}

@Preview
@Composable
fun RecipesIngredientsP() {
    val ingredients = listOf(
        RecipeIngredientModel(name = "Spaghetti", amount = 200.0),
        RecipeIngredientModel(name = "Pancetta", amount = 100.0),
        RecipeIngredientModel(name = "Eggs", amount = 2.0),
        RecipeIngredientModel(name = "Parmesan Cheese", amount = 50.0),
        RecipeIngredientModel(name = "Black Pepper", amount = 1.0),
        RecipeIngredientModel(name = "Salt", amount = 1.0)
    )
    RecipesIngredients(ingredients = ingredients)
}

@Composable
fun RecipeIngredientItem(modifier: Modifier = Modifier, name: String, amount: Double) {

    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(12))
            .background(color = Color.Green.copy(alpha = 0.2F))
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${amount.toInt()} $name",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}

@ThemePreviews
@Composable
fun RecipeIngredientItemP() {
    RecipeIngredientItem(name = "Black Pepper", amount = 1.0)
}