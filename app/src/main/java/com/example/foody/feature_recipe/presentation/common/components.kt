package com.example.foody.feature_recipe.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.foody.R
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    icon: ImageVector,
    contentDescription: String? = null,
    onActionClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title, style = MaterialTheme.typography.titleMedium)

        }, navigationIcon = {
            IconButton(onClick = onActionClicked) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription
                )
            }
        }
    )
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

@ThemePreviews
@Composable
fun CustomTopBarPreview() {
    FoodyTheme {
        CustomTopBar(
            title = stringResource(id = R.string.favourite_recipes), icon =
            Icons.AutoMirrored.Default.ArrowBack
        ) {}
    }
}