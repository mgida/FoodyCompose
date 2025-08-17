package com.example.foody.feature_recipe.presentation.home_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.presentation.mock.recipesItemsUIMock
import com.example.foody.feature_recipe.util.ThemePreviews

@Composable
fun RandomRecipes(
    recipes: List<RecipeModel>,
    onRecipeItemClick: (recipeId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.padding(horizontal = 8.dp),
    ) {
        items(recipes) { recipeModel ->

            RecipeItem(
                modifier = Modifier.padding(end = 8.dp),
                recipeId = recipeModel.id,
                recipeImage = recipeModel.image.orEmpty(),
                recipeTitle = recipeModel.title.orEmpty(),
                onRecipeItemClick = onRecipeItemClick,
            )
        }
    }
}

@ThemePreviews
@Composable
private fun RandomRecipesPreview() {
    RandomRecipes(
        recipes = recipesItemsUIMock,
        onRecipeItemClick = {},
    )
}
