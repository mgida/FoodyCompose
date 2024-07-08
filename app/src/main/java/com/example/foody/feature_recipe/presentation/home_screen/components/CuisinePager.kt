package com.example.foody.feature_recipe.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foody.feature_recipe.presentation.search_recipes_screen.components.RecipeImage
import com.example.foody.ui.theme.alphaRed
import com.example.foody.ui.theme.coolGray
import com.example.foody.ui.theme.mintCream
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CuisinePager(modifier: Modifier = Modifier, onPageClicked: (String) -> Unit) {
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = PAGER_COUNT,
        state = pagerState,
        modifier = modifier
            .height(560.dp)

    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clickable {
                    onPageClicked(
                        when (page) {
                            DISPLAY_SPANISH -> CUISINE.Spanish.name
                            DISPLAY_ITALIAN -> CUISINE.Italian.name
                            DISPLAY_AFRICAN -> CUISINE.African.name
                            else -> ""
                        }
                    )
                },
            contentAlignment = Alignment.TopCenter
        ) {
            when (page) {
                DISPLAY_SPANISH -> SpanishRecipes()
                DISPLAY_ITALIAN -> ItalianRecipes()
                DISPLAY_AFRICAN -> AfricanRecipes()
            }
        }
    }
}

@Composable
fun SpanishRecipes() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .height(560.dp)
            .background(coolGray),
        contentAlignment = Alignment.Center
    ) {
        BasicText(
            text = "${CUISINE.Spanish.name.uppercase()} FOOD",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = alphaRed
            ),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 32.dp)
        )

        Text(
            text = "#1",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp)
        )

        RecipeImage(
            imageUrl = "", modifier = Modifier
                .size(width = 320.dp, height = 390.dp)
                .align(
                    Alignment.BottomEnd
                )
        )
    }
}

@Composable
fun ItalianRecipes() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .height(560.dp)
            .background(mintCream),
        contentAlignment = Alignment.Center
    ) {
        BasicText(
            text = "${CUISINE.Italian.name.uppercase()} FOOD",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 32.dp)
        )

        Text(
            text = "#2",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp)
        )

        RecipeImage(
            imageUrl = "", modifier = Modifier
                .size(width = 320.dp, height = 390.dp)
                .align(
                    Alignment.BottomEnd
                )
        )
    }
}

@Composable
fun AfricanRecipes() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .height(560.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        BasicText(
            text = "${CUISINE.African.name.uppercase()} FOOD",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 32.dp)
        )

        Text(
            text = "#3",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp)
        )

        RecipeImage(
            imageUrl = "", modifier = Modifier
                .size(width = 320.dp, height = 390.dp)
                .align(
                    Alignment.BottomEnd
                )
        )
    }
}

@Preview
@Composable
fun CuisinePagerPreview() {
    CuisinePager(modifier = Modifier) {}
}

enum class CUISINE {
    African,
    Spanish,
    Italian
}

private const val PAGER_COUNT = 3
private const val DISPLAY_SPANISH = 0
private const val DISPLAY_ITALIAN = 1
private const val DISPLAY_AFRICAN = 2
