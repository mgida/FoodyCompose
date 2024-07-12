package com.example.foody.feature_recipe.domain.mapper

import com.example.foody.feature_recipe.data.dto.random_recipe.RecipeResponse
import com.example.foody.feature_recipe.domain.model.RecipeModel
import javax.inject.Inject

class UIRandomRecipeMapper @Inject constructor() : IMapper<RecipeResponse, RecipeModel> {

    override fun map(input: RecipeResponse): RecipeModel =
        RecipeModel(
            id = input.id,
            image = input.image,
            summary = input.summary,
            title = input.title
        )
}