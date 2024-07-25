package com.example.foody.feature_recipe.data.repo

import com.example.foody.feature_recipe.data.dto.recipe_information.RecipeInformationResponse
import com.example.foody.feature_recipe.data.dto.recipe_information.Taste
import com.example.foody.feature_recipe.data.dto.recipe_information.WinePairing
import com.example.foody.feature_recipe.data.dto.search_recipes.SearchRecipesResponse
import com.example.foody.feature_recipe.data.dto.search_recipes.SearchResult
import com.example.foody.feature_recipe.data.local.RecipeLocalDataSource
import com.example.foody.feature_recipe.data.remote.RecipeRemoteDataSource
import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.domain.repo.RecipeRepo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecipeRepoImplTest {

    @Mock
    private lateinit var recipeLocalDataSource: RecipeLocalDataSource

    @Mock
    private lateinit var recipeRemoteDataSource: RecipeRemoteDataSource

    private lateinit var recipeRepo: RecipeRepo

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        recipeRepo = RecipeRepoImpl(
            recipeLocalDataSource = recipeLocalDataSource,
            recipeRemoteDataSource = recipeRemoteDataSource
        )
    }

    @Test
    fun `searchRecipes should return valid response from remote data source`() = runBlocking {
        /** Arrange*/
        val searchRes = listOf(
            createSearchResult(id = 1, title = "pizza 1"),
            createSearchResult(id = 2, title = "pizza 2")
        )

        val response = SearchRecipesResponse(
            number = 2,
            offset = 0,
            results = searchRes,
            totalResults = 2
        )

        `when`(
            recipeRemoteDataSource.searchRecipes(
                apiKey = "123",
                cuisine = "Spanish",
                query = "pizza",

                )
        ).thenReturn(response)

        /** Act*/
        val result = recipeRepo.searchRecipes("123", "Spanish", "pizza")

        /** Assert*/
        assertThat(result).isEqualTo(response)
    }

    @Test
    fun `searchRecipes should return one searchResult`() = runBlocking {
        /** Arrange*/
        val searchRes = listOf(
            createSearchResult(id = 10, title = "pizza")
        )

        val response = SearchRecipesResponse(
            number = 1,
            offset = 0,
            results = searchRes,
            totalResults = 1
        )

        `when`(
            recipeRemoteDataSource.searchRecipes(
                apiKey = "123",
                cuisine = "Spanish",
                query = "pizza",

                )
        ).thenReturn(response)

        /** Act*/
        val result = recipeRepo.searchRecipes("123", "Spanish", "pizza")

        /** Assert*/
        assertThat(result.results.size).isEqualTo(1)
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

    @Test
    fun `getRecipeInformation should return same title from remote data source`() = runBlocking {
        /** Arrange*/
        val response = RecipeInformationResponse(
            aggregateLikes = 0,
            analyzedInstructions = listOf(),
            cheap = false,
            cookingMinutes = 0,
            creditsText = "",
            cuisines = listOf(),
            dairyFree = false,
            diets = listOf(),
            dishTypes = listOf(),
            extendedIngredients = listOf(),
            gaps = "",
            glutenFree = false,
            healthScore = 0,
            id = 100,
            image = "",
            imageType = "",
            instructions = "",
            lowFodmap = false,
            occasions = listOf(),
            originalId = 0,
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
            taste = Taste(
                bitterness = 0.0,
                fattiness = 0.0,
                saltiness = 0.0,
                savoriness = 0.0,
                sourness = 0.0,
                spiciness = 0.0,
                sweetness = 0.0
            ),
            title = "Burger",
            vegan = false,
            vegetarian = false,
            veryHealthy = false,
            veryPopular = false,
            weightWatcherSmartPoints = 0,
            winePairing = WinePairing(
                pairedWines = listOf(),
                pairingText = "",
                productMatches = listOf()
            )
        )

        `when`(
            recipeRemoteDataSource.getRecipeInformation(
                recipeId = 100,
                apiKey = "123"
            )
        ).thenReturn(response)

        /** Act*/
        val result = recipeRepo.getRecipeInformation(recipeId = 100, apiKey = "123")

        /** Assert*/
        assertThat(result.title).isEqualTo("Burger")
    }


    @Test
    fun `insertRecipe should call insertRecipe on local data source`() = runBlocking {

        val recipeModel = createRecipeModel(id = 88, title = "pizza")

        recipeRepo.insertRecipe(recipeModel)

        verify(recipeLocalDataSource).insertRecipe(recipeModel)
    }

    @Test
    fun `insertRecipe and retrieve by ID should return the same recipe`() = runBlocking {

        val recipeModel = createRecipeModel(id = 33, title = "pasta")

        `when`(recipeLocalDataSource.getRecipeById(recipeModel.id)).thenReturn(recipeModel)

        recipeRepo.insertRecipe(recipeModel)

        val retrievedRecipe = recipeRepo.getRecipeById(recipeModel.id)

        verify(recipeLocalDataSource).insertRecipe(recipeModel)
        assertThat(retrievedRecipe).isEqualTo(recipeModel)
    }


    @Test
    fun `insertRecipe, deleteRecipe, and getFavRecipes should return empty list`() = runBlocking {
        /** Arrange */
        val recipeModel = createRecipeModel(id = 22, title = "meat")

        val emptyListFlow = flowOf(emptyList<RecipeModel>())
        `when`(recipeLocalDataSource.getFavRecipes()).thenReturn(emptyListFlow)

        /** Act */
        recipeRepo.insertRecipes(listOf(recipeModel))
        recipeRepo.deleteRecipe(recipeModel)

        val favRecipes = recipeRepo.getFavRecipes().first()

        /** Assert */
        verify(recipeLocalDataSource).insertRecipes(listOf(recipeModel))
        verify(recipeLocalDataSource).deleteRecipe(recipeModel)
        assertThat(favRecipes).isEmpty()
    }


    @Test
    fun `insertTwoRecipes, deleteOneRecipe, and getFavRecipes should contain one recipe`() =
        runBlocking {
            /** Arrange */
            val recipe1 = createRecipeModel(id = 10, title = "pizza1")
            val recipe2 = createRecipeModel(id = 11, title = "pizza2")

            // Mock the behavior of localDataSource for getting the favorite recipes
            val oneRecipeFlow = flowOf(listOf(recipe2))
            `when`(recipeLocalDataSource.getFavRecipes()).thenReturn(oneRecipeFlow)

            /** Act */
            recipeRepo.insertRecipes(listOf(recipe1, recipe2))
            recipeRepo.deleteRecipe(recipe1)

            val favRecipes = recipeRepo.getFavRecipes().first()

            /** Assert */
            verify(recipeLocalDataSource).insertRecipes(listOf(recipe1, recipe2))
            verify(recipeLocalDataSource).deleteRecipe(recipe1)
            assertThat(favRecipes).hasSize(1)
            assertThat(favRecipes).contains(recipe2)
            assertThat(favRecipes).doesNotContain(recipe1)
        }


    private fun createRecipeModel(id: Int, title: String) = RecipeModel(
        id = id, image = "", title = title, summary = "", sourceUrl = "", isFav = false
    )
}