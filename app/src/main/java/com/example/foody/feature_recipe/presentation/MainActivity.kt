package com.example.foody.feature_recipe.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.foody.R
import com.example.foody.feature_recipe.presentation.recipes_screen.RecipesViewModel
import com.example.foody.ui.theme.FoodyTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipesScreen()
                }
            }
        }
    }
}

@Composable
fun RecipesScreen(viewModel: RecipesViewModel = hiltViewModel()) {
    val state = viewModel.state.value

    LazyColumn {
        items(state.recipes) {
            RecipeItem(it.title, it.image)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun RecipeItem(title: String = "", src: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(horizontalArrangement = Arrangement.Start) {

            Image(
                painter = rememberAsyncImagePainter(
                    src ?: "https://img.spoonacular.com/recipes/644604-556x370.jpg",
                    placeholder = painterResource(
                        id = R.drawable.ic_launcher_background
                    )
                ),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .heightIn(min = 180.dp)
                    .width(200.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title, style = TextStyle(fontSize = 16.sp, color = Color.Black))
        }
        HorizontalDivider(
            color = Color.Black.copy(alpha = 0.3F), modifier = Modifier
                .height(2.dp)
                .padding(top = 8.dp)
        )
    }
}

@Preview(heightDp = 100)
@Composable
fun RecipeItemPreview(modifier: Modifier = Modifier) {
    RecipeItem("My Recipe", null)
}
