package com.example.foody.feature_recipe.presentation.search_recipes_screen.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.BuildConfig
import com.example.foody.feature_recipe.domain.mapper.UISearchRecipesMapper
import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.domain.use_case.DeleteRecipeUseCase
import com.example.foody.feature_recipe.domain.use_case.GetRecentSearchesUseCase
import com.example.foody.feature_recipe.domain.use_case.SaveRecipeUseCase
import com.example.foody.feature_recipe.domain.use_case.SaveSearchQueryUseCase
import com.example.foody.feature_recipe.domain.use_case.SearchRecipesUseCase
import com.example.foody.feature_recipe.presentation.search_recipes_screen.SearchRecipesEvent
import com.example.foody.feature_recipe.presentation.search_recipes_screen.SearchRecipesState
import com.example.foody.feature_recipe.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase,
    private val saveRecipeUseCase: SaveRecipeUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase,
    private val uiSearchRecipesMapper: UISearchRecipesMapper,
    private val getRecentSearchesUseCase: GetRecentSearchesUseCase,
    private val saveSearchQueryUseCase: SaveSearchQueryUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _searchState = MutableStateFlow(SearchRecipesState())
    val searchState: StateFlow<SearchRecipesState> = _searchState

    private val _recentSearches = MutableStateFlow<Set<String>>(emptySet())
    val recentSearches: StateFlow<Set<String>> = _recentSearches.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>(RECIPE_CUISINE).also { cuisine ->
            cuisine?.let {
                searchRecipes(cuisine = cuisine)
                getRecentSearches()
            }
        }
    }

    fun onEvent(searchRecipesEvent: SearchRecipesEvent) {
        when (searchRecipesEvent) {

            is SearchRecipesEvent.GetRecipes -> {
                searchRecipes(query = searchRecipesEvent.query)
            }

            is SearchRecipesEvent.SaveRecipe -> {
                saveRecipe(searchRecipesEvent.searchRecipesModel)
            }

            is SearchRecipesEvent.DeleteRecipe -> {
                deleteRecipe(searchRecipesEvent.searchRecipesModel)
            }

            SearchRecipesEvent.GetRecentSearches -> {
                getRecentSearches()
            }

            is SearchRecipesEvent.SaveSearchQuery -> {
                saveSearchQuery(searchRecipesEvent.query)
            }
        }
    }

    private fun saveSearchQuery(query: String) {
        viewModelScope.launch {
            saveSearchQueryUseCase.invoke(query)
        }
    }

    private fun getRecentSearches() {
        viewModelScope.launch {
            getRecentSearchesUseCase.invoke().collect { searches ->
                _recentSearches.value = searches
            }
        }
    }

    private fun deleteRecipe(searchRecipesModel: RecipeModel) {
        try {
            viewModelScope.launch {
                deleteRecipeUseCase.invoke(searchRecipesModel = searchRecipesModel)

                toggleFavourite(searchRecipesModel)

                _eventFlow.emit(UiEvent.DeleteRecipe)
            }
        } catch (e: Exception) {
            Timber.e("Foody ${e.localizedMessage}")
        }
    }

    private fun toggleFavourite(recipe: RecipeModel) {

        val updatedRecipes = _searchState.value.recipes.map {
            if (it.id == recipe.id) {
                it.copy(isFav = recipe.isFav)
            } else {
                it
            }
        }
        _searchState.value = _searchState.value.copy(recipes = updatedRecipes)
    }

    private fun saveRecipe(searchRecipesModel: RecipeModel) {

        try {
            viewModelScope.launch {
                saveRecipeUseCase.invoke(searchRecipesModel = searchRecipesModel)

                toggleFavourite(searchRecipesModel)

                _eventFlow.emit(UiEvent.SaveRecipe)
            }

        } catch (e: Exception) {
            Timber.e("Foody ${e.localizedMessage}")
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

    sealed class UiEvent {
        data object SaveRecipe : UiEvent()
        data object DeleteRecipe : UiEvent()
    }

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
        private const val RECIPE_CUISINE = "recipeCuisine"
    }
}

