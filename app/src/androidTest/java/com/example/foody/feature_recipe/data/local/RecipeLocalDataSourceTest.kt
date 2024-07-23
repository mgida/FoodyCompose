package com.example.foody.feature_recipe.data.local

import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class RecipeLocalDataSourceTest {

    private lateinit var recipeDatabase: RecipeDatabase
    private lateinit var recipeLocalDataSource: RecipeLocalDataSource

    @Before
    fun setup() {
        recipeDatabase = inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RecipeDatabase::class.java
        ).allowMainThreadQueries().build()

        recipeLocalDataSource = recipeDatabase.recipeLocalDataSource
    }

    @After
    fun tearDown() {
        recipeDatabase.close()
    }

    @Test
    fun insertRecipe() {
        runTest {
            val recipeModel = createRecipeModel(id = 1, title = "pizza")

            recipeLocalDataSource.insertRecipe(recipeModel = recipeModel)

            val recipes = recipeLocalDataSource.getFavRecipes().first()

            assertThat(recipes).contains(recipeModel)
        }
    }

    @Test
    fun insertMultipleRecipes() {
        runTest {
            val recipes = listOf(
                createRecipeModel(id = 2, title = "Pasta"),
                createRecipeModel(id = 3, title = "Salad")
            )

            recipeLocalDataSource.insertRecipes(recipes = recipes)

            val favRecipes = recipeLocalDataSource.getFavRecipes().first()

            assertThat(favRecipes).isEqualTo(recipes)
        }
    }


    @Test
    fun deleteRecipe() {
        runTest {
            val recipeModel = createRecipeModel(id = 1, title = "pizza")

            recipeLocalDataSource.insertRecipe(recipeModel = recipeModel)
            recipeLocalDataSource.deleteRecipe(recipeModel = recipeModel)

            val recipes = recipeLocalDataSource.getFavRecipes().first()

            assertThat(recipes).doesNotContain(recipeModel)
        }
    }

    @Test
    fun deleteMultipleRecipes() {
        runTest {
            val recipes = listOf(
                createRecipeModel(id = 1, title = "pizza"),
                createRecipeModel(id = 2, title = "salad")
            )

            recipeLocalDataSource.insertRecipes(recipes = recipes)
            recipeLocalDataSource.deleteRecipes(recipes = recipes)

            val favRecipes = recipeLocalDataSource.getFavRecipes().first()

            assertThat(favRecipes).isEmpty()
        }
    }

    @Test
    fun countOfInsertedRecipes() {

        runTest {
            val recipes = listOf(
                createRecipeModel(id = 4, title = "Soup"),
                createRecipeModel(id = 5, title = "Burger")
            )

            recipeLocalDataSource.insertRecipes(recipes)
            val count = recipeLocalDataSource.getFavRecipes().first().size

            assertThat(count).isEqualTo(recipes.size)
        }
    }

    @Test
    fun countOfInsertedRecipesAfterDeletion() {
        val recipe = createRecipeModel(id = 6, title = "pasta")

        runTest {
            val recipes = listOf(
                recipe,
                createRecipeModel(id = 7, title = "meat")
            )

            recipeLocalDataSource.insertRecipes(recipes)
            recipeLocalDataSource.deleteRecipe(recipeModel = recipe)
            val count = recipeLocalDataSource.getFavRecipes().first().size

            assertThat(count).isEqualTo(recipes.size - 1)
        }
    }


    private fun createRecipeModel(
        id: Int,
        image: String = "https://example.com/image.jpg",
        title: String = "Recipe Title",
        summary: String = "Recipe Summary",
        sourceUrl: String = "https://example.com/recipe",
        isFav: Boolean = false
    ): RecipeModel {
        return RecipeModel(
            id = id,
            image = image,
            title = title,
            summary = summary,
            sourceUrl = sourceUrl,
            isFav = isFav
        )
    }
}