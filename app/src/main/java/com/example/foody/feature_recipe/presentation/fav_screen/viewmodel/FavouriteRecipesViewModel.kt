package com.example.foody.feature_recipe.presentation.fav_screen.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.domain.use_case.DeleteRecipeUseCase
import com.example.foody.feature_recipe.domain.use_case.GetFavRecipesUseCase
import com.example.foody.feature_recipe.domain.use_case.SaveRecipeUseCase
import com.example.foody.feature_recipe.presentation.fav_screen.FavouriteRecipesEvent
import com.example.foody.feature_recipe.presentation.fav_screen.FavouriteRecipesState
import com.example.foody.feature_recipe.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavouriteRecipesViewModel @Inject constructor(
    private val getSavedRecipesUseCase: GetFavRecipesUseCase,
    private val saveRecipeUseCase: SaveRecipeUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase,
) : ViewModel() {


    private val _favState = MutableStateFlow(FavouriteRecipesState())
    val favState: StateFlow<FavouriteRecipesState> = _favState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(favouriteRecipesEvent: FavouriteRecipesEvent) {
        when (favouriteRecipesEvent) {

            is FavouriteRecipesEvent.GetRecipes -> {
                getFavRecipes()
            }

            is FavouriteRecipesEvent.DeleteRecipe -> {
                deleteRecipeFromFavourites(favouriteRecipesEvent.recipeModel)
            }

            is FavouriteRecipesEvent.SaveRecipe -> {
                saveRecipeToFavourites(favouriteRecipesEvent.recipeModel)
            }
        }
    }

    private fun saveRecipeToFavourites(recipeModel: RecipeModel) {
        try {
            viewModelScope.launch {
                val favRecipe = recipeModel.copy(isFav = true)
                saveRecipeUseCase.invoke(favRecipe)

                _eventFlow.emit(UiEvent.SaveRecipe(recipeModel = favRecipe))
            }
        } catch (e: Exception) {
            Timber.e("Foody ${e.localizedMessage}")
        }
    }

    private fun deleteRecipeFromFavourites(recipeModel: RecipeModel) {
        try {
            viewModelScope.launch {
                deleteRecipeUseCase.invoke(recipeModel)

                _eventFlow.emit(UiEvent.DeleteRecipe(recipeModel = recipeModel))
            }
        } catch (e: Exception) {
            Timber.e("Foody ${e.localizedMessage}")
        }

    }

    private fun getFavRecipes() {
        getSavedRecipesUseCase.invoke().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _favState.value = FavouriteRecipesState(isLoading = true)
                }

                is Resource.Error -> {
                    _favState.value = FavouriteRecipesState(error = resource.message ?: "")
                }

                is Resource.Success -> {
                    _favState.value = FavouriteRecipesState(recipes = resource.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed class UiEvent {
        data class SaveRecipe(val recipeModel: RecipeModel) : UiEvent()
        data class DeleteRecipe(val recipeModel: RecipeModel) : UiEvent()
    }

}