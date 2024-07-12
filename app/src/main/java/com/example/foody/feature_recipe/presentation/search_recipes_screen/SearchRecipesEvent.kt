package com.example.foody.feature_recipe.presentation.search_recipes_screen

import com.example.foody.feature_recipe.domain.model.RecipeModel

sealed class SearchRecipesEvent {
    data class GetRecipes(val query: String) : SearchRecipesEvent()
    data class SaveRecipe(val searchRecipesModel: RecipeModel) : SearchRecipesEvent()
    data class DeleteRecipe(val searchRecipesModel: RecipeModel) : SearchRecipesEvent()
}