package com.example.foody.feature_recipe.presentation.recipe_details_screen

sealed class RecipeDetailEvent {
     class GetRecipeDetails(val recipeId: Int) : RecipeDetailEvent()
     class GetSimilarRecipes(val recipeId: Int) : RecipeDetailEvent()
}