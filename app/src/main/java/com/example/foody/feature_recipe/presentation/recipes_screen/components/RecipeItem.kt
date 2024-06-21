package com.example.foody.feature_recipe.presentation.recipes_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecipeItem(
    image: String,
    title: String,
    des: String,
    modifier: Modifier = Modifier,
    onShareClicked: () -> Unit,
    onFavClicked: () -> Unit,
    isFav: Boolean = false
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
                    .size(100.dp, 100.dp)
                    .clip(RoundedCornerShape(12)),
                imageUrl = image
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,


                )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = des,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onShareClicked
                ) {
                    Icon(
                        Icons.Default.Share,
                        contentDescription = "share",
                        tint = Color.Gray
                    )
                }

                IconButton(onClick = onFavClicked)
                {
                    Icon(
                        if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "fav",
                        tint = if (isFav) Color.Red else Color.Gray
                    )
                }

            }


        }
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun RecipeItemP() {
    MaterialTheme {
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