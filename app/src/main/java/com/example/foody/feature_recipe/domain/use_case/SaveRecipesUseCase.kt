package com.example.foody.feature_recipe.domain.use_case

import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import javax.inject.Inject

class SaveRecipesUseCase @Inject constructor(private val recipeRepo: RecipeRepo) {
    suspend operator fun invoke(recipes: List<RecipeModel>) {
        recipeRepo.insertRecipes(recipes)
    }
}