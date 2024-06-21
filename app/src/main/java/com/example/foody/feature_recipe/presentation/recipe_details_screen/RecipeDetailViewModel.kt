package com.example.foody.feature_recipe.presentation.recipe_details_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.BuildConfig
import com.example.foody.feature_recipe.domain.mapper.UIRecipeInformationMapper
import com.example.foody.feature_recipe.domain.mapper.UISimilarRecipesMapper
import com.example.foody.feature_recipe.domain.use_case.GetRecipeInformationUseCase
import com.example.foody.feature_recipe.domain.use_case.GetSimilarRecipesUseCase
import com.example.foody.feature_recipe.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeInformationUseCase: GetRecipeInformationUseCase,
    private val getSimilarRecipesUseCase: GetSimilarRecipesUseCase,
    private val recipeInformationMapper: UIRecipeInformationMapper,
    private val similarRecipesMapper: UISimilarRecipesMapper,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _recipeInformationState = MutableStateFlow(RecipeDetailState())
    val recipeInformationState: StateFlow<RecipeDetailState> = _recipeInformationState

    private val _similarRecipesState = MutableStateFlow(SimilarRecipesState())
    val similarRecipesState: StateFlow<SimilarRecipesState> = _similarRecipesState

    init {
        savedStateHandle.get<Int>(RECIPE_ID).also { noteId ->
            noteId?.let {
                getRecipeDetails(recipeId = it)
               // getSimilarRecipes(recipeId = it)
            }
        }
    }


    fun onEvent(recipeDetailEvent: RecipeDetailEvent) {
        when (recipeDetailEvent) {

            is RecipeDetailEvent.GetRecipeDetails -> {
                getRecipeDetails(recipeDetailEvent.recipeId)
            }

            is RecipeDetailEvent.GetSimilarRecipes -> {
                getSimilarRecipes(recipeDetailEvent.recipeId)
            }
        }
    }


    private fun getRecipeDetails(recipeId: Int) {

        getRecipeInformationUseCase.invoke(apiKey = API_KEY, recipeId = recipeId)
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        Timber.i("Foody getRecipeDetails: loading...")
                        _recipeInformationState.value = RecipeDetailState(isLoading = true)
                    }

                    is Resource.Success -> _recipeInformationState.value =
                        RecipeDetailState(
                            recipeInformationModel = resource.data?.let {
                                recipeInformationMapper.map(
                                    input = it
                                ).also { recipeInformationModel ->
                                    _recipeInformationState.value =
                                        RecipeDetailState(recipeInformationModel = recipeInformationModel)
                                }
                            }
                        )

                    is Resource.Error -> {
                        Timber.e("Foody getRecipeDetails: error ${resource.message} ")
                        _recipeInformationState.value =
                            RecipeDetailState(error = resource.message ?: "")
                    }
                }
            }.launchIn(viewModelScope)
    }


    private fun getSimilarRecipes(recipeId: Int) {

        getSimilarRecipesUseCase.invoke(apiKey = API_KEY, id = recipeId).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Timber.i("Foody similar recipes: loading...")
                    _similarRecipesState.value = SimilarRecipesState(isLoading = true)
                }

                is Resource.Success -> _similarRecipesState.value =
                    SimilarRecipesState(
                        similarRecipes = similarRecipesMapper.map(
                            resource.data?.toList() ?: listOf()
                        )
                    )

                is Resource.Error -> {
                    Timber.e("Foody getRecipeDetails: error ${resource.message} ")
                    _similarRecipesState.value =
                        SimilarRecipesState(error = resource.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
        private const val RECIPE_ID = "recipeId"
    }
}

