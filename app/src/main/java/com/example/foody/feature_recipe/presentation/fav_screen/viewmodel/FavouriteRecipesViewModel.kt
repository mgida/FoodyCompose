package com.example.foody.feature_recipe.presentation.fav_screen.viewmodel


import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.R
import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.domain.use_case.DeleteRecipeUseCase
import com.example.foody.feature_recipe.domain.use_case.DeleteRecipesUseCase
import com.example.foody.feature_recipe.domain.use_case.GetFavRecipesUseCase
import com.example.foody.feature_recipe.domain.use_case.SaveRecipeUseCase
import com.example.foody.feature_recipe.domain.use_case.SaveRecipesUseCase
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
    private val saveRecipesUseCase: SaveRecipesUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase,
    private val application: Application,
    private val deleteRecipesUseCase: DeleteRecipesUseCase,
) : AndroidViewModel(application) {


    private val _favState = MutableStateFlow(FavouriteRecipesState())
    val favState: StateFlow<FavouriteRecipesState> = _favState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var hasFetchedRecipes = false

    fun onEvent(favouriteRecipesEvent: FavouriteRecipesEvent) {
        when (favouriteRecipesEvent) {

            is FavouriteRecipesEvent.GetRecipes -> {
                if (!hasFetchedRecipes)
                    getFavRecipes()
            }

            is FavouriteRecipesEvent.DeleteRecipe -> {
                deleteRecipeFromFavourites(favouriteRecipesEvent.recipeModel)
            }

            is FavouriteRecipesEvent.SaveRecipe -> {
                saveRecipeToFavourites(favouriteRecipesEvent.recipeModel)
            }

            is FavouriteRecipesEvent.DeleteRecipes -> {
                deleteRecipes(favouriteRecipesEvent.recipes)
            }

            is FavouriteRecipesEvent.SaveRecipes -> {
                saveRecipesToFavourites(favouriteRecipesEvent.recipes)
            }

            is FavouriteRecipesEvent.ShareRecipe -> {
                shareRecipe(favouriteRecipesEvent.sourceUrl)
            }
        }
    }

    private fun shareRecipe(sourceUrl: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                application.getString(R.string.check_out_this_recipe, sourceUrl)
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        application.startActivity(shareIntent)
    }

    private fun saveRecipesToFavourites(recipes: List<RecipeModel>) {
        try {
            viewModelScope.launch {
                recipes.map {
                    it.copy(isFav = true)
                }.also { favRecipes ->
                    saveRecipesUseCase.invoke(favRecipes)
                    _eventFlow.emit(UiEvent.MultipleRecipesSaved(recipes = favRecipes))
                }
            }
        } catch (e: Exception) {
            Timber.e("Foody ${e.localizedMessage}")
        }
    }

    private fun deleteRecipes(recipes: List<RecipeModel>) {
        try {
            viewModelScope.launch {
                deleteRecipesUseCase.invoke(recipes)

                _eventFlow.emit(UiEvent.MultipleRecipesDeleted(recipes))
            }
        } catch (e: Exception) {
            Timber.e("Foody ${e.localizedMessage}")
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
        hasFetchedRecipes = true
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
        data class MultipleRecipesSaved(val recipes: List<RecipeModel>) : UiEvent()
        data class DeleteRecipe(val recipeModel: RecipeModel) : UiEvent()
        data class MultipleRecipesDeleted(val recipes: List<RecipeModel>) : UiEvent()
    }

}