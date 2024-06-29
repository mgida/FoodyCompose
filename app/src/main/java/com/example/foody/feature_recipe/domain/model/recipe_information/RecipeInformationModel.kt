package com.example.foody.feature_recipe.domain.model.recipe_information

data class RecipeInformationModel(
    val id: Int?,
    val image: String?,
    val instructions: String?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val summary: String?,
    val title: String?,
    val ingredients : List<RecipeIngredientModel>?
)
