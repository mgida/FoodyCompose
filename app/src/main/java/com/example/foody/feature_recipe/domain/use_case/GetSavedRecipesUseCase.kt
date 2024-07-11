package com.example.foody.feature_recipe.domain.use_case

import com.example.foody.feature_recipe.domain.model.search_recipes.SearchRecipesModel
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedRecipesUseCase @Inject constructor(private val recipeRepo: RecipeRepo) {
    operator fun invoke(): Flow<List<SearchRecipesModel>> {
        return recipeRepo.getRecipes()
    }
}