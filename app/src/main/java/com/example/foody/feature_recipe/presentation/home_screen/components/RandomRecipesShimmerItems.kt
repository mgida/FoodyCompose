package com.example.foody.feature_recipe.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foody.feature_recipe.util.ThemePreviews
import com.example.foody.feature_recipe.util.shimmerEffect
import com.example.foody.ui.theme.FoodyTheme


@Composable
fun RandomRecipesShimmerItems(modifier: Modifier = Modifier) {

    LazyRow(modifier = modifier) {
        items(8) {
            Column(
                modifier = modifier
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 170.dp, height = 98.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .shimmerEffect()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .height(15.dp)
                        .width(100.dp)
                        .shimmerEffect()
                )
            }
        }
    }


}

@ThemePreviews
@Composable
fun RecipesShimmerItemsPreview() {
    FoodyTheme {
        RandomRecipesShimmerItems(modifier = Modifier.fillMaxWidth())
    }
}