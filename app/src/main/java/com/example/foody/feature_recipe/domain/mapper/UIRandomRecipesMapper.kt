package com.example.foody.feature_recipe.domain.mapper

import com.example.foody.feature_recipe.data.dto.random_recipe.RecipeResponse
import com.example.foody.feature_recipe.domain.model.RecipeModel
import javax.inject.Inject

class UIRandomRecipesMapper @Inject constructor(
    private val uiRandomRecipeMapper: UIRandomRecipeMapper
) : IMapper<List<RecipeResponse>, List<RecipeModel>> {
    override fun map(input: List<RecipeResponse>): List<RecipeModel> {
        val recipes = mutableListOf<RecipeModel>()
        for (recipeResponse in input) {
            recipes.add(uiRandomRecipeMapper.map(input = recipeResponse))
        }
        return recipes
    }
}