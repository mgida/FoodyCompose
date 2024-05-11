package com.example.foody.feature_recipe.domain.use_case

import com.example.foody.feature_recipe.data.dto.SimilarRecipesResponse
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import com.example.foody.feature_recipe.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSimilarRecipesUseCase @Inject constructor(private val recipeRepo: RecipeRepo) {
    operator fun invoke(id: Int, apiKey: String): Flow<Resource<SimilarRecipesResponse>> = flow {
        emit(Resource.Loading())
        try {
            val similarRecipes = recipeRepo.getSimilarRecipes(id, apiKey)
            emit(Resource.Success(similarRecipes))

        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: ""))
        }
    }
}