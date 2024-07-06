package com.example.foody.feature_recipe.presentation.home_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.foody.R
import com.example.foody.feature_recipe.util.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(modifier: Modifier = Modifier) {

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "For You",
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_food_menu),
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    )
}


@ThemePreviews
@Composable
fun CustomAppBarPreview() {
    CustomAppBar()
}