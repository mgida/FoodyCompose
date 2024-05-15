package com.example.foody.feature_recipe.domain.mapper

import com.example.foody.feature_recipe.data.dto.random_recipe.RecipeResponse
import com.example.foody.feature_recipe.domain.model.random_recipe.RandomRecipeModel
import javax.inject.Inject

class UIRandomRecipeMapper @Inject constructor() : IMapper<RecipeResponse, RandomRecipeModel> {

    override fun map(input: RecipeResponse): RandomRecipeModel =
        RandomRecipeModel(
            id = input.id,
            image = input.image,
            readyInMinutes = input.readyInMinutes,
            servings = input.servings,
            summary = input.summary,
            title = input.title,
        )
}