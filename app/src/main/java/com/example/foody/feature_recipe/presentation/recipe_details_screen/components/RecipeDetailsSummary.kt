package com.example.foody.feature_recipe.presentation.recipe_details_screen.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foody.feature_recipe.util.RECIPE_TITLE_TRANSITION_KEY
import com.example.foody.feature_recipe.util.TWEEN_DURATION
import com.example.foody.feature_recipe.util.rememberHtmlText

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RecipeDetailsSummary(
    title: String,
    summary: String,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        with(sharedTransitionScope) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                modifier = Modifier.sharedElement(
                    sharedContentState = rememberSharedContentState(key = "$RECIPE_TITLE_TRANSITION_KEY/$title"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = TWEEN_DURATION)
                    }
                )
            )
        }

        Spacer(modifier = Modifier.height(6.dp))


        Text(
            text = summary.rememberHtmlText(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black.copy(alpha = 0.7F)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Preview(widthDp = 400, showBackground = true)
@Composable
fun RecipeDetailsSummaryPreview() {
//    RecipeDetailsSummary(
//        title = "Spaghetti Carbonara",
//        summary = "Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs might be a good recipe to expand your main course repertoire. One portion of this dish contains approximately <b>19g of protein </b>,  <b>20g of fat </b>, and a total of  <b>584 calories </b>. For  <b>\$1.63 per serving </b>, this recipe  <b>covers 23% </b> of your daily requirements of vitamins and minerals. This recipe serves 2. It is brought to you by fullbellysisters.blogspot.com. 209 people were glad they tried this recipe."
//    )
}