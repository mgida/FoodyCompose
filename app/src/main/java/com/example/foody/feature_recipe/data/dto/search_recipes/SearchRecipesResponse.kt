package com.example.foody.feature_recipe.data.dto.search_recipes

data class SearchRecipesResponse(
    val number: Int,
    val offset: Int,
    val results: List<SearchResult>,
    val totalResults: Int
)