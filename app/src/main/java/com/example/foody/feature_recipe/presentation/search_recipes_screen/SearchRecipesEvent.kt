package com.example.foody.feature_recipe.presentation.search_recipes_screen

import com.example.foody.feature_recipe.domain.model.search_recipes.SearchRecipesModel

sealed class SearchRecipesEvent {
    data class GetRecipes(val query: String) : SearchRecipesEvent()
    data class SaveRecipe(val searchRecipesModel: SearchRecipesModel) : SearchRecipesEvent()
    data class DeleteRecipe(val searchRecipesModel: SearchRecipesModel) : SearchRecipesEvent()
}