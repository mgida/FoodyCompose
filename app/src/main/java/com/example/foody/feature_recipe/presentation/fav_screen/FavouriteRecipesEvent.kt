package com.example.foody.feature_recipe.presentation.fav_screen

import com.example.foody.feature_recipe.domain.model.RecipeModel

sealed class FavouriteRecipesEvent {
    data object GetRecipes : FavouriteRecipesEvent()
    data class DeleteRecipe(val recipeModel: RecipeModel) : FavouriteRecipesEvent()
    data class SaveRecipe(val recipeModel: RecipeModel) : FavouriteRecipesEvent()
    data class SaveRecipes(val recipes: List<RecipeModel>) : FavouriteRecipesEvent()
    data class DeleteRecipes(val recipes: List<RecipeModel>) : FavouriteRecipesEvent()
}