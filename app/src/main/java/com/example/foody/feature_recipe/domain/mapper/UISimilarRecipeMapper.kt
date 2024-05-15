package com.example.foody.feature_recipe.domain.mapper

import com.example.foody.feature_recipe.data.dto.similar_recipe.SimilarRecipesResponseItem
import com.example.foody.feature_recipe.domain.model.similar_recipe.SimilarRecipeModel

class UISimilarRecipeMapper : IMapper<SimilarRecipesResponseItem, SimilarRecipeModel> {
    override fun map(input: SimilarRecipesResponseItem): SimilarRecipeModel = SimilarRecipeModel(
        id = input.id,
        imageType = input.imageType,
        readyInMinutes = input.readyInMinutes,
        servings = input.servings,
        sourceUrl = input.sourceUrl,
        title = input.title,
    )
}