package com.example.foody.feature_recipe.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foody.feature_recipe.util.ThemePreviews
import com.example.foody.ui.theme.FoodyTheme

@Composable
fun ErrorState(modifier: Modifier = Modifier, errorMsg: String) {
    Box(
        modifier = modifier
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorMsg,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun EmptyResult(modifier: Modifier = Modifier, msg: String) {
    Box(
        modifier = modifier
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Text(
            text = msg,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
    }
}

@ThemePreviews
@Composable
fun ErrorStatePreview() {
    FoodyTheme {
        ErrorState(errorMsg = "Something went wrong!")
    }
}

@ThemePreviews
@Composable
fun EmptyResultPreview() {
    FoodyTheme {
        EmptyResult(msg = "No Result Found!")
    }
}