package com.example.foody.feature_recipe.data.remote

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeRemoteDataSourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var recipeRemoteDataSource: RecipeRemoteDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        recipeRemoteDataSource = retrofit.create(RecipeRemoteDataSource::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `search recipes should return valid response`() {

        runBlocking {
            val mockResponse = MockResponse()
            mockResponse.setBody(loadJson("search_recipes_response.json"))

            mockWebServer.enqueue(response = mockResponse)

            val response =
                recipeRemoteDataSource.searchRecipes(
                    apiKey = "123456",
                    cuisine = "Italian",
                    number = 3
                )

            assertThat(response.results).hasSize(3)
        }
    }

    @Test
    fun `get random recipes should return valid response`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockResponse.setBody(loadJson("random_recipes_response.json"))

            mockWebServer.enqueue(response = mockResponse)

            val response =
                recipeRemoteDataSource.getRandomRecipes(apiKey = "123456", number = 2)

            assertThat(response.recipes).hasSize(2)
        }
    }

    private fun loadJson(fileName: String): String {
        val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")
        return inputStream?.bufferedReader()?.use { it.readText() }
            ?: throw IllegalArgumentException("File not found: $fileName")
    }
}