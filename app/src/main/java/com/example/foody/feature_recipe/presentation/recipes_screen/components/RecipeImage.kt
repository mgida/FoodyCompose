package com.example.foody.feature_recipe.presentation.recipes_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.foody.R

@Composable
fun RecipeImage(
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = imageUrl ?: R.drawable.ic_launcher_foreground)
            .apply(block = fun ImageRequest.Builder.() {
                transformations(RoundedCornersTransformation(12F))
                placeholder(R.drawable.ic_launcher_foreground)
                crossfade(true)
                error(R.drawable.ic_launcher_foreground)
            }).build()
    )
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Preview
@Composable
fun RecipeImagePreview() {
    RecipeImage(
        imageUrl = IMAGE_URL,
        modifier = Modifier
            .size(width = 130.dp, height = 120.dp)
            .clip(RoundedCornerShape(12.dp))
    )
}

private const val IMAGE_URL = ""