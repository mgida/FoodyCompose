package com.example.foody.feature_recipe.presentation.recipes_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.BuildConfig
import com.example.foody.feature_recipe.domain.use_case.GetRandomRecipesUseCase
import com.example.foody.feature_recipe.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(private val useCase: GetRandomRecipesUseCase) :
    ViewModel() {

    private val _state = mutableStateOf(RandomRecipesState())
    val state: State<RandomRecipesState> = _state

    init {
        getRandomRecipes()
    }


    fun onEvent(randomRecipesEvent: RandomRecipesEvent) {
        when (randomRecipesEvent) {

            is RandomRecipesEvent.GetRecipes -> {
                getRandomRecipes()
            }
        }
    }


    private fun getRandomRecipes() {
        viewModelScope.launch {
            when (val res = useCase.invoke(API_KEY)) {
                is Resource.Success -> _state.value =
                    state.value.copy(recipes = res.data?.recipes ?: listOf())

                is Resource.Error -> {
                    Timber.e("Foody getRandomRecipes: error ${res.message} ")
                }

                is Resource.Loading -> {
                    Timber.i("Foody getRandomRecipes: loading...")
                }
            }
        }
    }

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }
}

