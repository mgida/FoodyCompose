package com.example.foody.feature_recipe.presentation.search_recipes_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.foody.feature_recipe.util.ThemePreviews
import com.example.foody.feature_recipe.util.shimmerEffect

@Composable
fun SearchRecipesShimmerItems(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(10, key = { it }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    modifier =
                        Modifier
                            .padding(8.dp)
                            .size(100.dp)
                            .clip(
                                RoundedCornerShape(12),
                            ).shimmerEffect(),
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier =
                        Modifier
                            .padding(vertical = 16.dp)
                            .weight(1f),
                ) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp)
                                .height(20.dp)
                                .shimmerEffect(),
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(end = 32.dp)
                                .height(10.dp)
                                .shimmerEffect(),
                    )
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun RecipesShimmerItemsPreview() {
    SearchRecipesShimmerItems(modifier = Modifier.fillMaxWidth())
}
