package com.example.foody.feature_recipe.presentation.fav_screen.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.feature_recipe.domain.use_case.GetFavRecipesUseCase
import com.example.foody.feature_recipe.presentation.fav_screen.FavouriteRecipesEvent
import com.example.foody.feature_recipe.presentation.fav_screen.FavouriteRecipesState
import com.example.foody.feature_recipe.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavouriteRecipesViewModel @Inject constructor(
    private val getSavedRecipesUseCase: GetFavRecipesUseCase
) : ViewModel() {


    private val _favState = MutableStateFlow(FavouriteRecipesState())
    val favState: StateFlow<FavouriteRecipesState> = _favState

    fun onEvent(favouriteRecipesEvent: FavouriteRecipesEvent) {
        when (favouriteRecipesEvent) {

            is FavouriteRecipesEvent.GetRecipes -> {
                getFavRecipes()
            }
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

}