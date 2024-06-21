package com.example.foody.feature_recipe.presentation.recipe_details_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foody.feature_recipe.presentation.recipes_screen.components.RecipeImage

@Composable
fun RecipeDetailsImage(image: String?, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        RecipeImage(imageUrl = image, modifier = modifier.fillMaxSize())
    }
}

@Preview
@Composable
fun RecipeDetailsImagePreview() {
    RecipeDetailsImage(image = "")
}
