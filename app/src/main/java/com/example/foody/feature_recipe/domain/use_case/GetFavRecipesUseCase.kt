package com.example.foody.feature_recipe.domain.use_case

import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import com.example.foody.feature_recipe.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

class GetFavRecipesUseCase @Inject constructor(private val recipeRepo: RecipeRepo) {
    operator fun invoke(): Flow<Resource<List<RecipeModel>>> =
        flow {
            emit(Resource.Loading())

            try {
                recipeRepo.getFavRecipes().onEach {
                    Timber.i("Foody.. fav $it")
                    emit(Resource.Success(it))
                }.collect()

            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: ""))
            }
        }
}