package com.example.foody.feature_recipe.domain.use_case

import app.cash.turbine.test
import com.example.foody.feature_recipe.data.dto.search_recipes.SearchRecipesResponse
import com.example.foody.feature_recipe.data.dto.search_recipes.SearchResult
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import com.example.foody.feature_recipe.util.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchRecipesUseCaseTest {

    @Mock
    private lateinit var recipeRepo: RecipeRepo

    private lateinit var searchRecipesUseCase: SearchRecipesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        searchRecipesUseCase = SearchRecipesUseCase(recipeRepo = recipeRepo)
    }


    @Test
    fun `invoke should emit Resource Success with correct data`() = runTest {
        val apiKey = "123"
        val cuisine = "Spanish"
        val query = "pizza"

        val searchRes = listOf(
            createSearchResult(id = 1, title = "pizza 1"),
            createSearchResult(id = 2, title = "pizza 2")
        )

        val searchResponse = SearchRecipesResponse(
            number = 2,
            offset = 0,
            results = searchRes,
            totalResults = 2
        )

        `when`(
            recipeRepo.searchRecipes(
                apiKey = apiKey,
                cuisine = cuisine,
                query = query
            )
        ).thenReturn(searchResponse)

        searchRecipesUseCase.invoke(apiKey, cuisine, query).test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            val success = awaitItem() as Resource.Success
            assertThat(success.data).isEqualTo(searchResponse)
            awaitComplete()
        }
    }


    @Test
    fun `invoke should emit Resource Error when repository fails`() = runTest {
        val apiKey = "123"
        val cuisine = "Spanish"
        val query = "pizza"
        val errorMessage = "something went wrong!"


        `when`(recipeRepo.searchRecipes(apiKey, cuisine, query)).thenThrow(
            RuntimeException(errorMessage)
        )

        searchRecipesUseCase.invoke(apiKey, cuisine, query).test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
            awaitComplete()
        }
    }
}


private fun createSearchResult(id: Int, title: String) = SearchResult(
    aggregateLikes = 0,
    cheap = false,
    cookingMinutes = 0,
    creditsText = "",
    cuisines = listOf(),
    dairyFree = false,
    diets = listOf(),
    dishTypes = listOf(),
    gaps = "",
    glutenFree = false,
    healthScore = 0,
    id = id,
    image = "",
    imageType = "",
    lowFodmap = false,
    occasions = listOf(),
    preparationMinutes = 0,
    pricePerServing = 0.0,
    readyInMinutes = 0,
    servings = 0,
    sourceName = "",
    sourceUrl = "",
    spoonacularScore = 0.0,
    spoonacularSourceUrl = "",
    summary = "",
    sustainable = false,
    title = title,
    vegan = false,
    vegetarian = false,
    veryHealthy = false,
    veryPopular = false,
    weightWatcherSmartPoints = 0
)

