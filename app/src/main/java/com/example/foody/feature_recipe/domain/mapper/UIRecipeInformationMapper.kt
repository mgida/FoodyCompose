package com.example.foody.feature_recipe.domain.mapper

import com.example.foody.feature_recipe.data.dto.recipe_information.RecipeInformationResponse
import com.example.foody.feature_recipe.domain.model.recipe_information.RecipeInformationModel
import javax.inject.Inject

class UIRecipeInformationMapper @Inject constructor() :
    IMapper<RecipeInformationResponse, RecipeInformationModel> {

    override fun map(input: RecipeInformationResponse): RecipeInformationModel =
        RecipeInformationModel(
            id = input.id,
            image = input.image,
            instructions = input.instructions,
            readyInMinutes = input.readyInMinutes,
            servings = input.servings,
            summary = input.summary,
            title = input.title
        )
}