package com.example.foody.feature_recipe.presentation.recipe_details_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foody.feature_recipe.domain.model.recipe_information.RecipeInformationModel
import com.example.foody.feature_recipe.domain.model.recipe_information.RecipeIngredientModel
import com.example.foody.feature_recipe.domain.model.similar_recipe.SimilarRecipeModel
import com.example.foody.feature_recipe.presentation.common.ErrorState
import com.example.foody.feature_recipe.presentation.recipe_details_screen.components.RecipeDetailsImage
import com.example.foody.feature_recipe.presentation.recipe_details_screen.components.RecipeDetailsServings
import com.example.foody.feature_recipe.presentation.recipe_details_screen.components.RecipeDetailsSummary
import com.example.foody.feature_recipe.presentation.recipe_details_screen.components.RecipesIngredients
import com.example.foody.feature_recipe.presentation.recipe_details_screen.components.SimilarRecipeItem
import com.example.foody.feature_recipe.util.ThemePreviews
import timber.log.Timber

@Composable
fun RecipeDetailsScreen(
    modifier: Modifier = Modifier, viewModel: RecipeDetailViewModel = hiltViewModel(), recipeId: Int
) {

    val recipeInformationState = viewModel.recipeInformationState.collectAsState().value
    val similarRecipesState = viewModel.similarRecipesState.collectAsState().value

    SideEffect {
        Timber.d("Foody recipeId: $recipeId")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        RecipeContentSection(recipeInformationState, modifier)
        // SimilarRecipesSection(similarRecipesState, modifier)
    }
}

@Composable
fun SimilarRecipesSection(similarRecipesState: SimilarRecipesState, modifier: Modifier) {
    when {
        similarRecipesState.isLoading -> {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = modifier)
            }
        }

        similarRecipesState.error.isNotBlank() -> {
            ErrorState(errorMsg = similarRecipesState.error)
        }

        else -> {
            val similarRecipes = similarRecipesState.similarRecipes
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(
                    similarRecipes,
                    key = { item: SimilarRecipeModel ->
                        item.id
                    },
                ) { similarRecipe ->
                    SimilarRecipeItem(similarRecipe)
                }
            }
        }
    }
}

@Composable
private fun RecipeContentSection(
    recipeInformationState: RecipeDetailState,
    modifier: Modifier = Modifier
) {
    when {
        recipeInformationState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = modifier)
            }
        }

        recipeInformationState.error.isNotBlank() -> {
            ErrorState(errorMsg = recipeInformationState.error)
        }

        else -> {
            val recipe = recipeInformationState.recipeInformationModel
            RecipeContent(modifier, recipe)
        }
    }
}

@Composable
private fun RecipeContent(
    modifier: Modifier,
    recipe: RecipeInformationModel?
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {

        item { RecipeDetailsImage(recipe?.image) }
        item {
            RecipeDetailsSummary(
                recipe?.title ?: "",
                recipe?.summary ?: ""
            )
        }
        item {
            RecipeDetailsServings(
                recipe?.servings,
                recipe?.readyInMinutes
            )
        }

        item {
            IngredientHeader()
        }

        item {
            recipe?.ingredients?.let {
                RecipesIngredients(ingredients = it)
            }
        }
    }
}

@Composable
fun IngredientHeader(modifier: Modifier = Modifier) {
    Text(
        text = "Ingredients",
        style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    )
}

@ThemePreviews
@Composable
fun RecipeDetailsScreenP() {
    RecipeContent(Modifier, sampleRecipe)
}

val sampleRecipe = RecipeInformationModel(
    id = 1,
    image = "https://example.com/image1.jpg",
    instructions = "1. Boil the pasta.\n2. Fry the pancetta.\n3. Mix eggs and cheese.\n4. Combine all ingredients.",
    readyInMinutes = 30,
    servings = 4,
    summary = "A classic Italian pasta dish made with eggs, cheese, pancetta, and pepper.",
    title = "Spaghetti Carbonara",
    ingredients = listOf(
        RecipeIngredientModel(name = "Spaghetti", amount = 200.0),
        RecipeIngredientModel(name = "Pancetta", amount = 100.0),
        RecipeIngredientModel(name = "Eggs", amount = 2.0),
        RecipeIngredientModel(name = "Parmesan Cheese", amount = 50.0),
        RecipeIngredientModel(name = "Black Pepper", amount = 1.0),
        RecipeIngredientModel(name = "Salt", amount = 1.0)
    )
)




