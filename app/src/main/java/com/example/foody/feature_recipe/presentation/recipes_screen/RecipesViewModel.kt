package com.example.foody.feature_recipe.presentation.recipes_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.BuildConfig
import com.example.foody.feature_recipe.domain.use_case.GetRandomRecipesUseCase
import com.example.foody.feature_recipe.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(private val useCase: GetRandomRecipesUseCase) :
    ViewModel() {

    private val _state = mutableStateOf(RandomRecipesState())
    val state: State<RandomRecipesState> = _state

    init {
        getRandomRecipes()
    }

    private fun getRandomRecipes() {
        viewModelScope.launch {
            when (val res = useCase.invoke(API_KEY)) {
                is Resource.Success -> _state.value =
                    state.value.copy(recipes = res.data?.recipes ?: listOf())

                is Resource.Error -> {
                    Log.d("Foody", "getRandomRecipes: error ${res.message} ")
                }

                is Resource.Loading -> {
                    Log.d("Foody", "getRandomRecipes: loading....")
                }
            }
        }

    }

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }
}

