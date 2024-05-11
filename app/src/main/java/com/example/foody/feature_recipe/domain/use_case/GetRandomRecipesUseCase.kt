package com.example.foody.feature_recipe.domain.use_case

import com.example.foody.feature_recipe.domain.model.random_recipe.RandomRecipeResponse
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import com.example.foody.feature_recipe.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRandomRecipesUseCase @Inject constructor(private val recipeRepo: RecipeRepo) {
     operator fun invoke(apiKey: String): Flow<Resource<RandomRecipeResponse>> =

        flow {
            emit(Resource.Loading())
            try {
                val randomRecipesResponse = recipeRepo.getRandomRecipes(apiKey)
                emit(Resource.Success(randomRecipesResponse))
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: ""))
            }
        }
}