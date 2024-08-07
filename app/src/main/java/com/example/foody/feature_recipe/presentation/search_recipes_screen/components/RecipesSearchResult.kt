package com.example.foody.feature_recipe.presentation.search_recipes_screen.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.util.OrientationPreviews
import com.example.foody.feature_recipe.util.ThemePreviews

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RecipesSearchResult(
    recipes: List<RecipeModel>,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    selectedRecipes: MutableList<RecipeModel> = mutableListOf(),
    onRecipeClicked: (id: Int) -> Unit,
    onFavClicked: (searchRecipeModel: RecipeModel) -> Unit,
    onShareClicked: (source: String) -> Unit,
) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(recipes, key = { recipeModel ->
            recipeModel.id
        }) { recipeItem ->
            RecipeItem(
                recipeId = recipeItem.id,
                image = recipeItem.image ?: "",
                title = recipeItem.title ?: "",
                des = recipeItem.summary ?: "",
                sourceUrl = recipeItem.sourceUrl ?: "",
                isFav = recipeItem.isFav,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                isSelected = selectedRecipes.contains(recipeItem),
                onToggleSelection = { isSelected ->
                    if (isSelected) {
                        selectedRecipes.add(recipeItem)
                    } else {
                        selectedRecipes.remove(recipeItem)
                    }
                },
                onRecipeClicked = { recipeId ->
                    onRecipeClicked(recipeId)
                },
                onShareClicked = { source ->
                    onShareClicked.invoke(source)
                },
                onFavClicked = {
                    onFavClicked.invoke(it)
                }
            )
            HorizontalDivider(
                color = Color.Gray.copy(alpha = 0.3F),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}


@ThemePreviews
@OrientationPreviews
@Composable
fun RecipesSearchResultPreview() {
    val recipes =
        listOf(
            RecipeModel(
                id = 1,
                image = "",
                title = "Lorem Ipsum",
                summary = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution",
                sourceUrl = ""
            ),
            RecipeModel(
                id = 2,
                image = "",
                title = "Contrary to popula",
                summary = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form",
                sourceUrl = ""
            ),
            RecipeModel(
                id = 3,
                image = "",
                title = "He standard chunk of Lorem",
                summary = "literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in",
                sourceUrl = ""
            ),
            RecipeModel(
                id = 4,
                image = "",
                title = "There are many variations",
                summary = "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and",
                sourceUrl = ""
            ),
            RecipeModel(
                id = 5,
                image = "",
                title = "Produced below for those",
                summary = "but also the leap into electronic typesetting, remaining essentially unchanged",
                sourceUrl = ""
            )
        )


//    FoodyTheme {
//
//        RecipesSearchResult(
//            recipes = recipes,
//            sharedTransitionScope =,
//            animatedVisibilityScope =
//        ) {}
//
//    }
}