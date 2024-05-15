package com.example.foody.feature_recipe.domain.use_case

import com.example.foody.feature_recipe.data.dto.recipe_information.RecipeInformationResponse
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import com.example.foody.feature_recipe.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecipeInformationUseCase @Inject constructor(private val recipeRepo: RecipeRepo) {
    operator fun invoke(
        apiKey: String,
        recipeId: Int
    ): Flow<Resource<RecipeInformationResponse?>> = flow {
        emit(Resource.Loading())
        try {
            val recipeInformation =
                recipeRepo.getRecipeInformation(apiKey = apiKey, recipeId = recipeId)
            emit(Resource.Success(recipeInformation))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: ""))
        }
    }
}