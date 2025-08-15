package com.example.foody.feature_recipe.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.foody.R
import com.example.foody.feature_recipe.util.ThemePreviews
import com.example.foody.feature_recipe.util.formatErrorMessage
import com.example.foody.ui.theme.FoodyTheme

@Composable
fun ErrorState(
    errorMsg: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Box(
        modifier =
            modifier
                .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = errorMsg.formatErrorMessage(context = context),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun EmptyResult(
    msg: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = msg,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black.copy(alpha = 0.6F),
            textAlign = TextAlign.Center,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    icon: ImageVector,
    onActionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
        },
        navigationIcon = {
            IconButton(onClick = onActionClicked) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                )
            }
        },
    )
}

@ThemePreviews
@Composable
private fun ErrorStatePreview() {
    FoodyTheme {
        ErrorState(errorMsg = "Something went wrong!")
    }
}

@ThemePreviews
@Composable
private fun EmptyResultPreview() {
    FoodyTheme {
        EmptyResult(msg = "No Result Found!")
    }
}

@ThemePreviews
@Composable
private fun CustomTopBarPreview() {
    FoodyTheme {
        CustomTopBar(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.favourite_recipes),
            icon = Icons.AutoMirrored.Default.ArrowBack,
            onActionClicked = {},
        )
    }
}
