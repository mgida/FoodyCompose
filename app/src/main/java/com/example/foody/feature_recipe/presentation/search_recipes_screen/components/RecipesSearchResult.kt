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
    onRecipeClick: (id: Int) -> Unit,
    onFavClick: (searchRecipeModel: RecipeModel) -> Unit,
    onShareClick: (source: String) -> Unit,
    onToggleSelection: (isSelected: Boolean, recipeItem: RecipeModel) -> Unit,
    modifier: Modifier = Modifier,
    selectedRecipes: List<RecipeModel> = listOf(),
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(recipes, key = { recipeModel ->
            recipeModel.id
        }) { recipeItem ->
            RecipeItem(
                recipeId = recipeItem.id,
                image = recipeItem.image.orEmpty(),
                title = recipeItem.title.orEmpty(),
                des = recipeItem.summary.orEmpty(),
                sourceUrl = recipeItem.sourceUrl.orEmpty(),
                isFav = recipeItem.isFav,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                isSelected = selectedRecipes.contains(recipeItem),
                onToggleSelection = { isSelected ->
                    onToggleSelection(isSelected, recipeItem)
                },
                onRecipeClick = onRecipeClick,
                onShareClick = onShareClick,
                onFavClick = onFavClick,
            )
            HorizontalDivider(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                color = Color.Gray.copy(alpha = 0.3F),
            )
        }
    }
}

@ThemePreviews
@OrientationPreviews
@Composable
private fun RecipesSearchResultPreview() {
    val recipes =
        listOf(
            RecipeModel(
                id = 1,
                image = "",
                title = "Lorem Ipsum",
                summary = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution",
                sourceUrl = "",
            ),
            RecipeModel(
                id = 2,
                image = "",
                title = "Contrary to popula",
                summary = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form",
                sourceUrl = "",
            ),
            RecipeModel(
                id = 3,
                image = "",
                title = "He standard chunk of Lorem",
                summary = "literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in",
                sourceUrl = "",
            ),
            RecipeModel(
                id = 4,
                image = "",
                title = "There are many variations",
                summary = "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and",
                sourceUrl = "",
            ),
            RecipeModel(
                id = 5,
                image = "",
                title = "Produced below for those",
                summary = "but also the leap into electronic typesetting, remaining essentially unchanged",
                sourceUrl = "",
            ),
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
