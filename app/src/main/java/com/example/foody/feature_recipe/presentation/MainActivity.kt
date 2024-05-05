package com.example.foody.feature_recipe.presentation

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.foody.R
import com.example.foody.ui.theme.FoodyTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    RecipesNavHost(
                        navController = navController,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}





@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    title: String = "",
    des: String = "",
    src: String?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = rememberAsyncImagePainter(
                    src ?: "https://img.spoonacular.com/recipes/644604-556x370.jpg",
                    placeholder = painterResource(
                        id = R.drawable.ic_launcher_foreground
                    )
                ),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 130.dp, height = 120.dp)
                    .clip(RoundedCornerShape(percent = 12))
//                    .height(120.dp)
//                    .width(130.dp)

            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = des,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7F),
                    modifier = Modifier.padding(top = 6.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        HorizontalDivider(
            color = Color.Gray.copy(alpha = 0.3F),
            modifier = Modifier
                .height(2.dp)
                .padding(top = 16.dp)
        )
    }
}

@Preview(heightDp = 120, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(heightDp = 120, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RecipeItemPreview(modifier: Modifier = Modifier) {
    FoodyTheme {
        Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            RecipeItem(
                title = "My Recipe",
                des = "If you have approximately <b>45 minutes</b> to spend in the kitchen, Salsa Verde Chicken Tamales might be a super",
                src = null
            )
        }
    }
}
