package com.example.foody.feature_recipe.domain.mapper

import com.example.foody.feature_recipe.data.dto.search_recipes.SearchResult
import com.example.foody.feature_recipe.domain.model.RecipeModel
import javax.inject.Inject

class UISearchRecipesMapper @Inject constructor(
    private val uiSearchRecipeMapper: UISearchRecipeMapper
) : IMapper<List<SearchResult>, List<RecipeModel>> {
    override fun map(input: List<SearchResult>): List<RecipeModel> {
        val recipes = mutableListOf<RecipeModel>()
        for (recipeResponse in input) {
            recipes.add(uiSearchRecipeMapper.map(input = recipeResponse))
        }
        return recipes
    }
}