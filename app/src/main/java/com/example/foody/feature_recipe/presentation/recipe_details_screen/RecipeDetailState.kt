package com.example.foody.feature_recipe.presentation.recipe_details_screen

import com.example.foody.feature_recipe.domain.model.recipe_information.RecipeInformationModel

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipeInformationModel: RecipeInformationModel? = null,
    val error: String = ""
)
