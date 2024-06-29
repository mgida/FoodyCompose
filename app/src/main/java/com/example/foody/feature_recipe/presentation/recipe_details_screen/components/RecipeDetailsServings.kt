package com.example.foody.feature_recipe.presentation.recipe_details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun RecipeDetailsServings(servings: Int?, readyInMinutes: Int?, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(top = 8.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background(color = Color.LightGray.copy(alpha = 0.2F))
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Serving $servings",
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Ready with in $readyInMinutes min",
            color = Color.Black,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(widthDp = 400, showBackground = true)
@Composable
fun RecipeDetailsServingsPreview() {
    RecipeDetailsServings(
        servings = 3,
        readyInMinutes = 45
    )
}