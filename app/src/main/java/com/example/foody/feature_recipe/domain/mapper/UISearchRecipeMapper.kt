package com.example.foody.feature_recipe.domain.mapper

import com.example.foody.feature_recipe.data.dto.search_recipes.SearchResult
import com.example.foody.feature_recipe.domain.model.RecipeModel
import javax.inject.Inject

class UISearchRecipeMapper @Inject constructor() : IMapper<SearchResult, RecipeModel> {
    override fun map(input: SearchResult): RecipeModel =
        RecipeModel(
            id = input.id,
            image = input.image,
            title = input.title,
            summary = input.summary
        )
}