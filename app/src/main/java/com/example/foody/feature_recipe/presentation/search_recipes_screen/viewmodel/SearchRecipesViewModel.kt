package com.example.foody.feature_recipe.presentation.search_recipes_screen.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.BuildConfig
import com.example.foody.feature_recipe.domain.mapper.UISearchRecipesMapper
import com.example.foody.feature_recipe.domain.use_case.SearchRecipesUseCase
import com.example.foody.feature_recipe.presentation.search_recipes_screen.SearchRecipesEvent
import com.example.foody.feature_recipe.presentation.search_recipes_screen.SearchRecipesState
import com.example.foody.feature_recipe.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase,
    private val uiSearchRecipesMapper: UISearchRecipesMapper,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _searchState = MutableStateFlow(SearchRecipesState())
    val searchState: StateFlow<SearchRecipesState> = _searchState

    init {
        savedStateHandle.get<String>(RECIPE_CUISINE).also { cuisine ->
            cuisine?.let {
                searchRecipes(cuisine = cuisine)
            }
        }
    }

    fun onEvent(searchRecipesEvent: SearchRecipesEvent) {
        when (searchRecipesEvent) {

            is SearchRecipesEvent.GetRecipes -> {
                searchRecipes(query = searchRecipesEvent.query)
            }
        }
    }

    private fun searchRecipes(cuisine: String = "", query: String = "") {

        searchRecipesUseCase.invoke(API_KEY, cuisine, query).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Timber.i("Foody search Recipes: loading...")
                    _searchState.value = SearchRecipesState(isLoading = true)
                }

                is Resource.Success -> _searchState.value =
                    SearchRecipesState(
                        recipes = uiSearchRecipesMapper.map(
                            input = resource.data?.results ?: listOf()
                        )
                    )

                is Resource.Error -> {
                    Timber.e("Foody search Recipes: error ${resource.message} ")
                    _searchState.value = SearchRecipesState(error = resource.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
        private const val RECIPE_CUISINE = "recipeCuisine"
    }
}

