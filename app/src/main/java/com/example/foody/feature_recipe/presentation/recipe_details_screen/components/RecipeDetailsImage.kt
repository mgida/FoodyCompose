package com.example.foody.feature_recipe.presentation.recipe_details_screen.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foody.feature_recipe.presentation.search_recipes_screen.components.RecipeImage
import com.example.foody.feature_recipe.util.RECIPE_IMAGE_TRANSITION_KEY
import com.example.foody.feature_recipe.util.TWEEN_DURATION

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RecipeDetailsImage(
    recipeId: Int?,
    image: String?,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {

        with(sharedTransitionScope) {
            RecipeImage(imageUrl = image, modifier = modifier
                .fillMaxSize()
                .sharedElement(
                    state = rememberSharedContentState(key = "${RECIPE_IMAGE_TRANSITION_KEY}/$recipeId"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = TWEEN_DURATION)
                    }
                )
            )
        }
    }
}

@Preview
@Composable
fun RecipeDetailsImagePreview() {
    //  RecipeDetailsImage(image = "")
}
