package com.example.foody.feature_recipe.domain.mapper

import com.example.foody.feature_recipe.data.dto.search_recipes.SearchResult
import com.example.foody.feature_recipe.domain.model.search_recipes.SearchRecipesModel
import javax.inject.Inject

class UISearchRecipeMapper @Inject constructor() : IMapper<SearchResult, SearchRecipesModel> {
    override fun map(input: SearchResult): SearchRecipesModel =
        SearchRecipesModel(
            id = input.id,
            image = input.image,
            title = input.title,
            summary = input.summary
        )
}