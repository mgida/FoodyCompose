package com.example.foody.feature_recipe.presentation.search_recipes_screen.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foody.R
import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.util.FontScalePreviews
import com.example.foody.feature_recipe.util.LayoutDirectionPreviews
import com.example.foody.feature_recipe.util.RECIPE_IMAGE_TRANSITION_KEY
import com.example.foody.feature_recipe.util.RECIPE_TITLE_TRANSITION_KEY
import com.example.foody.feature_recipe.util.ThemePreviews
import com.example.foody.feature_recipe.util.rememberHtmlText
import com.example.foody.ui.theme.blueGray
import com.example.foody.ui.theme.delicateWhite

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalFoundationApi::class)
@Composable
fun RecipeItem(
    recipeId: Int,
    image: String,
    title: String,
    des: String,
    isFav: Boolean,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    isSelected: Boolean = false,
    onToggleSelection: (isSelected: Boolean) -> Unit,
    onRecipeClicked: (recipeId: Int) -> Unit,
    onShareClicked: () -> Unit,
    onFavClicked: (searchRecipeModel: RecipeModel) -> Unit
) {

    var isFavourite by remember {
        mutableStateOf(isFav)
    }
    val backgroundColor by animateColorAsState(
        if (isSelected) delicateWhite else Color.White,
        label = ""
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .combinedClickable(
                onClick = { onRecipeClicked(recipeId) },
                onLongClick = {
                    onToggleSelection(!isSelected)
                }
            )
            .padding(horizontal = 8.dp, vertical = 8.dp)


    ) {

        Box(
            modifier = Modifier
                .padding(end = 8.dp)
                .clickable {
                    onRecipeClicked(recipeId)
                }, contentAlignment = Alignment.Center
        ) {

            with(sharedTransitionScope) {
                RecipeImage(
                    modifier = Modifier
                        .size(125.dp, 100.dp)
                        .clip(RoundedCornerShape(12))
                        .sharedElement(
                            state = rememberSharedContentState(key = "${RECIPE_IMAGE_TRANSITION_KEY}/$recipeId"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 500)
                            }
                        ),
                    imageUrl = image
                )
            }

        }

        Column(modifier = Modifier.weight(1f)) {

            with(sharedTransitionScope) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "${RECIPE_TITLE_TRANSITION_KEY}/$title"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 500)
                        }
                    )
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = des.rememberHtmlText(),
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                color = Color.Black.copy(alpha = 0.7F),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                CustomIcon(
                    modifier = Modifier,
                    icon = R.drawable.ic_share,
                    des = "Share",
                    onClick = onShareClicked,
                    isFav = false,
                )
                Spacer(modifier = Modifier.width(8.dp))
                CustomIcon(
                    modifier = Modifier,
                    icon = if (!isFavourite) R.drawable.ic_fav_outlined else R.drawable.ic_fav_filled,
                    des = "Fav",
                    isFav = isFavourite,
                    onClick = {

                        isFavourite = !isFavourite

                        onFavClicked(
                            RecipeModel(
                                id = recipeId,
                                image = image,
                                title = title,
                                summary = des,
                                isFav = isFavourite
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun CustomIcon(
    modifier: Modifier = Modifier,
    icon: Int,
    des: String,
    onClick: () -> Unit,
    isFav: Boolean
) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = des,
        tint = if (isFav) Color.Red else blueGray,
        modifier = modifier
            .size(18.dp, 18.dp)
            .clickable(onClick = onClick)
    )
}

@ThemePreviews
@FontScalePreviews
@LayoutDirectionPreviews
@Composable
fun RecipeItemP() {

//    FoodyTheme {
//        RecipeItem(
//            recipeId = 1,
//            image = "",
//            title = "simply dummy text",
//            des =
//            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
//                    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
//                    " when an unknown printer took a galley of type and scrambled it to make a type" +
//                    " specimen book",
//            onShareClicked = {},
//            onFavClicked = {}
//        )
//    }
}