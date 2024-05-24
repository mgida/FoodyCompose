package com.example.foody.feature_recipe.domain.mapper

import com.example.foody.feature_recipe.data.dto.similar_recipe.SimilarRecipesResponseItem
import com.example.foody.feature_recipe.domain.model.similar_recipe.SimilarRecipeModel
import javax.inject.Inject

class UISimilarRecipesMapper @Inject constructor(
    private val uiSimilarRecipeMapper: UISimilarRecipeMapper
) : IMapper<List<SimilarRecipesResponseItem>, List<SimilarRecipeModel>> {
    override fun map(input: List<SimilarRecipesResponseItem>): List<SimilarRecipeModel> {
        val recipes = mutableListOf<SimilarRecipeModel>()
        for (recipeResponse in input) {
            recipes.add(uiSimilarRecipeMapper.map(input = recipeResponse))
        }
        return recipes
    }
}