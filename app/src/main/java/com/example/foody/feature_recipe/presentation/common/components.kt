package com.example.foody.feature_recipe.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foody.feature_recipe.util.ThemePreviews
import com.example.foody.ui.theme.FoodyTheme

@Composable
fun ErrorState(errorMsg: String) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), contentAlignment = Alignment.Center) {
        Text(
            text = errorMsg,
            color = MaterialTheme.colorScheme.error
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