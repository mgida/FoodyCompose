package com.example.foody.feature_recipe.domain.mapper

import com.example.foody.feature_recipe.data.dto.recipe_information.ExtendedIngredientResponse
import com.example.foody.feature_recipe.domain.model.recipe_information.RecipeIngredientModel
import javax.inject.Inject

class UIRecipeIngredientsMapper @Inject constructor() :
    IMapper<List<ExtendedIngredientResponse>, List<RecipeIngredientModel>> {

    override fun map(input: List<ExtendedIngredientResponse>): List<RecipeIngredientModel> {
        val ingredients = mutableListOf<RecipeIngredientModel>()
        for (ingredient in input) {
            ingredients.add(
                RecipeIngredientModel(
                    name = ingredient.name,
                    amount = ingredient.amount
                )
            )
        }
        return ingredients
    }
}