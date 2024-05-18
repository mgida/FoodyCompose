package com.example.foody.feature_recipe.presentation.recipe_details_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.BuildConfig
import com.example.foody.feature_recipe.domain.mapper.UIRecipeInformationMapper
import com.example.foody.feature_recipe.domain.use_case.GetRecipeInformationUseCase
import com.example.foody.feature_recipe.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val useCase: GetRecipeInformationUseCase,
    private val mapper: UIRecipeInformationMapper,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _state = mutableStateOf(RecipeDetailState())
    val state: State<RecipeDetailState> = _state

    init {
        savedStateHandle.get<Int>(RECIPE_ID).also { noteId ->
            noteId?.let {
                getRecipeDetails(recipeId = it)
            }
        }
    }


    fun onEvent(recipeDetailEvent: RecipeDetailEvent) {
        when (recipeDetailEvent) {

            is RecipeDetailEvent.GetRecipeDetails -> {
                getRecipeDetails(recipeDetailEvent.recipeId)
            }
        }
    }


    private fun getRecipeDetails(recipeId: Int) {

        useCase.invoke(apiKey = API_KEY, recipeId = recipeId).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Timber.i("Foody getRecipeDetails: loading...")
                    _state.value = RecipeDetailState(isLoading = true)
                }

                is Resource.Success -> _state.value =
                    RecipeDetailState(
                        recipeInformationModel = resource.data?.let {
                            mapper.map(
                                input = it
                            ).also { recipeInformationModel ->
                                _state.value =
                                    RecipeDetailState(recipeInformationModel = recipeInformationModel)
                            }
                        }
                    )

                is Resource.Error -> {
                    Timber.e("Foody getRecipeDetails: error ${resource.message} ")
                    _state.value = RecipeDetailState(error = resource.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
        private const val RECIPE_ID = "recipeId"
    }
}

