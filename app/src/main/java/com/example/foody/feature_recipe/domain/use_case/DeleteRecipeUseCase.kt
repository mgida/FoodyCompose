package com.example.foody.feature_recipe.domain.use_case

import com.example.foody.feature_recipe.domain.model.search_recipes.SearchRecipesModel
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import javax.inject.Inject

class DeleteRecipeUseCase @Inject constructor(private val recipeRepo: RecipeRepo) {
    suspend operator fun invoke(searchRecipesModel: SearchRecipesModel) {
        recipeRepo.deleteRecipe(searchRecipesModel)
    }

}