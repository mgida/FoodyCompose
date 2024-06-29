package com.example.foody.feature_recipe.presentation.recipes_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foody.R
import com.example.foody.feature_recipe.util.FontScalePreviews
import com.example.foody.feature_recipe.util.LayoutDirectionPreviews
import com.example.foody.feature_recipe.util.ThemePreviews
import com.example.foody.feature_recipe.util.rememberHtmlText
import com.example.foody.ui.theme.FoodyTheme
import com.example.foody.ui.theme.blueGray

@Composable
fun RecipeItem(
    image: String,
    title: String,
    des: String,
    modifier: Modifier = Modifier,
    onShareClicked: () -> Unit,
    onFavClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 8.dp)

    ) {

        Box(modifier = Modifier.padding(end = 8.dp), contentAlignment = Alignment.Center) {
            RecipeImage(
                modifier = Modifier
                    .size(125.dp, 100.dp)
                    .clip(RoundedCornerShape(12)),
                imageUrl = image
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
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
                    onClick = onShareClicked
                )
                Spacer(modifier = Modifier.width(8.dp))
                CustomIcon(
                    modifier = Modifier,
                    icon = R.drawable.ic_fav,
                    des = "Fav",
                    onClick = onFavClicked
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
    tint: Color = blueGray
) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = des,
        tint = tint,
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
    FoodyTheme {
        RecipeItem(
            image = "",
            title = "simply dummy text",
            des =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                    " when an unknown printer took a galley of type and scrambled it to make a type" +
                    " specimen book",
            onShareClicked = {},
            onFavClicked = {}
        )
    }
}