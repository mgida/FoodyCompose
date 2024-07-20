package com.example.foody.feature_recipe.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.foody.R
import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.presentation.search_recipes_screen.components.RecipeImage
import com.example.foody.feature_recipe.util.ThemePreviews
import com.example.foody.ui.theme.softWhite

@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    recipeModel: RecipeModel,
    onRecipeItemClicked: (recipeId: Int) -> Unit
) {

    ConstraintLayout(
        modifier = modifier
            .background(softWhite)
            .padding(end = 8.dp)
            .clickable {
                onRecipeItemClicked.invoke(recipeModel.id)
            }
    ) {
        val (image, text, shareIcon, favIcon) = createRefs()
        val endGuideline = createGuidelineFromEnd(0.35f)


        RecipeImage(
            modifier = Modifier
                .size(width = 170.dp, height = 98.dp)
                .constrainAs(image) {
                    start.linkTo(parent.start, margin = 8.dp)
                    top.linkTo(parent.top, margin = 8.dp)
                }, imageUrl = recipeModel.image ?: ""
        )

        Text(
            text = recipeModel.title ?: "",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(text) {
                start.linkTo(image.start)
                end.linkTo(endGuideline)
                top.linkTo(image.bottom, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
                width = Dimension.fillToConstraints
            }
        )

        Icon(
            modifier = Modifier
                .size(18.dp)
                .constrainAs(shareIcon) {
                    start.linkTo(text.end, margin = 8.dp)
                    top.linkTo(text.top, margin = 8.dp)
                    bottom.linkTo(text.bottom, margin = 8.dp)
                },
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            modifier = Modifier
                .size(18.dp)
                .constrainAs(favIcon) {
                    start.linkTo(shareIcon.end, margin = 8.dp)
                    top.linkTo(text.top, margin = 8.dp)
                    bottom.linkTo(text.bottom, margin = 8.dp)
                },
            painter = painterResource(id = R.drawable.ic_fav_outlined),
            contentDescription = null
        )

    }
}

@ThemePreviews
@Composable
fun RecipeItemPreview() {

    val recipe = RecipeModel(
        id = 10,
        image = "",
        title = "Lemon Garlic Salmon",
        summary = "A simple and delicious dish featuring salmon fillets marinated in lemon juice and garlic.",
        sourceUrl = ""
    )
    RecipeItem(recipeModel = recipe) {}
}