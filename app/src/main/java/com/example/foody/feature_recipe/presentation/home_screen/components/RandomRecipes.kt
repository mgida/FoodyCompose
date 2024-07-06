package com.example.foody.feature_recipe.presentation.home_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foody.feature_recipe.domain.model.random_recipe.RandomRecipeModel
import com.example.foody.feature_recipe.util.ThemePreviews

@Composable
fun RandomRecipes(modifier: Modifier = Modifier, recipes: List<RandomRecipeModel>) {

    LazyRow(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        items(recipes) { recipeModel ->
            RecipeItem(modifier = Modifier, recipeModel)
        }
    }
}

@ThemePreviews
@Composable
fun RandomRecipesPreview() {
    val recipes = listOf(
        RandomRecipeModel(
            id = 1,
            image = "",
            title = "Spaghetti Carbonara",
            summary = "A classic Italian pasta dish made with eggs, cheese, pancetta, and pepper."
        ),
        RandomRecipeModel(
            id = 2,
            image = "",
            title = "Chicken Tikka Masala",
            summary = "A popular Indian dish made with grilled chunks of chicken enveloped in a creamy tomato curry."
        ),
        RandomRecipeModel(
            id = 3,
            image = "",
            title = "Beef Stroganoff",
            summary = "A Russian dish of sautéed pieces of beef served in a sauce with sour cream."
        ),
        RandomRecipeModel(
            id = 4,
            image = "",
            title = "Vegetable Stir Fry",
            summary = "A quick and healthy dish made with a mix of fresh vegetables stir-fried in a savory sauce."
        ),
        RandomRecipeModel(
            id = 5,
            image = "",
            title = "Chocolate Chip Cookies",
            summary = "Classic cookies filled with gooey chocolate chips, perfect for any occasion."
        )
    )

    RandomRecipes(recipes = recipes)
}
