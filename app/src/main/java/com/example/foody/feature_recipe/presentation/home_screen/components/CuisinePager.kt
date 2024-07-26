package com.example.foody.feature_recipe.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foody.R
import com.example.foody.ui.theme.alphaRed
import com.example.foody.ui.theme.delicateWhite
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
    CustomCuisineBox(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .height(560.dp)
            .background(delicateWhite),
        cuisine = "${CUISINE.Spanish.name.uppercase()} FOOD",
        color = alphaRed,
        page = "#1",
        recipesCount = 10,
        imageRes = R.drawable.spanish_recipe_beans
    )
}

@Composable
private fun CustomCuisineBox(
    modifier: Modifier = Modifier,
    cuisine: String,
    color: Color,
    page: String,
    recipesCount: Int,
    imageRes: Int = R.drawable.spanish_recipe
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier =
            Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 32.dp)
        ) {
            BasicText(
                text = cuisine,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = alphaRed
                ),
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                text = stringResource(R.string.recipes, recipesCount),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp),
                color = alphaRed
            )
        }

        Text(
            text = page,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = color,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp)
        )

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp))
                .size(width = 320.dp, height = 390.dp)
                .align(
                    Alignment.BottomEnd
                )

        )
    }
}

@Composable
fun ItalianRecipes() {
    CustomCuisineBox(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .height(560.dp)
            .background(delicateWhite),
        cuisine = "${CUISINE.Italian.name.uppercase()} FOOD",
        color = alphaRed,
        page = "#2",
        recipesCount = 8,
        imageRes = R.drawable.italian_recipe
    )
}

@Composable
fun AfricanRecipes() {
    CustomCuisineBox(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .height(560.dp)
            .background(delicateWhite),
        cuisine = "${CUISINE.African.name.uppercase()} FOOD",
        color = alphaRed,
        page = "#3",
        recipesCount = 6,
        imageRes = R.drawable.african_recipe
    )
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
