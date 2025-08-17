package com.example.foody.feature_recipe.presentation.mock

import com.example.foody.feature_recipe.domain.model.RecipeModel

val recipeItemUIMock =
    RecipeModel(
        id = 1,
        title = "Spaghetti Carbonara",
        image = "https://example.com/spaghetti-carbonara.jpg",
        summary = "A classic Italian pasta dish made with eggs, cheese, pancetta, and pepper.",
        sourceUrl = "https://example.com/spaghetti-carbonara-recipe",
        isFav = false,
    )

val recipesItemsUIMock =
    (1..5).map { index ->
        RecipeModel(
            id = index,
            title = "Recipe $index",
            image = "https://example.com/recipe-$index.jpg",
            summary = "This is a summary for recipe $index.",
            sourceUrl = "https://example.com/recipe-$index",
            isFav = index % 2 == 0,
        )
    }
