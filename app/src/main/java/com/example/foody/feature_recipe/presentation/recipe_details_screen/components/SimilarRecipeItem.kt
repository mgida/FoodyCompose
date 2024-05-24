package com.example.foody.feature_recipe.presentation.recipe_details_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foody.feature_recipe.domain.model.similar_recipe.SimilarRecipeModel
import com.example.foody.feature_recipe.presentation.recipes_screen.components.RecipeImage

@Composable
fun SimilarRecipeItem(it: SimilarRecipeModel, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .width(200.dp)
            .wrapContentHeight()
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {

        RecipeImage(
            imageUrl = it.sourceUrl,
            modifier = Modifier.size(width = 150.dp, height = 150.dp)
        )

        Text(
            text = it.title ?: "",
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = it.readyInMinutes.toString(),
            style = MaterialTheme.typography.bodySmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = Color.Red
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SimilarRecipeItemPreview() {
    val recipe = SimilarRecipeModel(
        id = 2,
        title = "Fettuccine Alfredo Fettuccine Alfredo Fettuccine Alfredo Fettuccine AlfredoFettuccine Alfredo Fettuccine Alfredo",
        sourceUrl = "https://img.spoonacular.com/recipes/642605-556x370.jpg",
        readyInMinutes = 40,
        servings = 3
    )

    SimilarRecipeItem(it = recipe)
}