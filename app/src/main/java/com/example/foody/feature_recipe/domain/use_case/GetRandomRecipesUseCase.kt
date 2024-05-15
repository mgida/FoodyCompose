package com.example.foody.feature_recipe.domain.use_case

import com.example.foody.feature_recipe.util.Resource
import com.example.foody.feature_recipe.domain.model.random_recipe.RandomRecipeResponse
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import javax.inject.Inject

class GetRandomRecipesUseCase @Inject constructor(private val recipeRepo: RecipeRepo) {
    suspend operator fun invoke(apiKey: String): Resource<RandomRecipeResponse> {
        Resource.Loading(data = null)
        try {

            val res = recipeRepo.getRandomRecipes(apiKey)

            res.data?.let {
                return Resource.Success(it)
            }

        } catch (e: Exception) {
            return Resource.Error(message = e.localizedMessage ?: "", null)
        }

        return Resource.Success(data = RandomRecipeResponse(recipes = listOf()))
    }

}