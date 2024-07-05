package com.example.foody.feature_recipe.domain.use_case

import com.example.foody.feature_recipe.data.dto.search_recipes.SearchRecipesResponse
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import com.example.foody.feature_recipe.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(private val recipeRepo: RecipeRepo) {
    operator fun invoke(apiKey: String, cuisine: String, query: String): Flow<Resource<SearchRecipesResponse>> =

        flow {
            emit(Resource.Loading())
            try {
                val recipes =
                    recipeRepo.searchRecipes(apiKey = apiKey, cuisine = cuisine, query= query)

                emit(Resource.Success(recipes))
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: ""))
            }
        }
}