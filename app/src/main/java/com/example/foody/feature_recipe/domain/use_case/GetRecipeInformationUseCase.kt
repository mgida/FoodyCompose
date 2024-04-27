package com.example.foody.feature_recipe.domain.use_case

import com.example.foody.feature_recipe.domain.model.recipe_information.RecipeInformationResponse
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import com.example.foody.feature_recipe.util.Resource
import javax.inject.Inject

class GetRecipeInformationUseCase @Inject constructor(private val recipeRepo: RecipeRepo) {
    suspend operator fun invoke(
        apiKey: String,
        recipeId: Int
    ): Resource<RecipeInformationResponse?> {
        Resource.Loading(data = null)
        try {

            val res = recipeRepo.getRecipeInformation(apiKey = apiKey, recipeId = recipeId)

            res.data?.let {
                return Resource.Success(it)
            }

        } catch (e: Exception) {
            return Resource.Error(message = e.localizedMessage ?: "", null)
        }

        return Resource.Success(null)
    }
}