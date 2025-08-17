package com.example.foody

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foody.feature_recipe.domain.model.RecipeModel
import com.example.foody.feature_recipe.presentation.home_screen.RandomRecipesState
import com.example.foody.feature_recipe.presentation.home_screen.components.HomeContent
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testShimmerItemsDisplayedWhenLoading() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        runTest {
            composeTestRule.setContent {
                HomeContent(
                    modifier = Modifier.fillMaxSize(),
                    onNavigate = {},
                    onNavigateToFav = {},
                    onRecipeItemClick = {},
                    state =
                        RandomRecipesState(
                            isLoading = true,
                            recipes = listOf(),
                            error = "",
                        ),
                )
            }
            composeTestRule
                .onNode(hasTestTag(context.getString(R.string.shimmer_items_tag)))
                .assertExists()
        }
    }

    @Test
    fun testErrorStateDisplayedWhenError() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val errorMsg = context.getString(R.string.error_message)

        runTest {
            composeTestRule.setContent {
                HomeContent(
                    modifier = Modifier.fillMaxSize(),
                    onNavigate = {},
                    onNavigateToFav = {},
                    onRecipeItemClick = {},
                    state =
                        RandomRecipesState(
                            isLoading = false,
                            recipes = listOf(),
                            error = errorMsg,
                        ),
                )
            }
            composeTestRule.onNode(hasTestTag(errorMsg)).assertExists()
        }
    }

    @Test
    fun testRecipesDisplayedWhenAvailable() {
        val recipes =
            listOf(
                RecipeModel(
                    id = 1,
                    image = "image1.jpg",
                    title = "Recipe 1",
                    summary = "",
                    sourceUrl = "",
                    isFav = false,
                ),
                RecipeModel(
                    id = 2,
                    image = "image2.jpg",
                    title = "Recipe 2",
                    summary = "",
                    sourceUrl = "",
                    isFav = false,
                ),
            )

        runTest {
            composeTestRule.setContent {
                HomeContent(
                    modifier = Modifier.fillMaxSize(),
                    onNavigate = {},
                    onNavigateToFav = {},
                    onRecipeItemClick = {},
                    state =
                        RandomRecipesState(
                            isLoading = false,
                            recipes = recipes,
                            error = "",
                        ),
                )
            }

            recipes.forEach { recipe ->
                composeTestRule.onNode(hasText(recipe.title!!)).assertExists()
            }
        }
    }

    @Test
    fun testEmptyStateDisplayedWhenNoRecipes() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val emptyMsg = context.getString(R.string.nothing_found)

        runTest {
            composeTestRule.setContent {
                HomeContent(
                    modifier = Modifier.fillMaxSize(),
                    onNavigate = {},
                    onNavigateToFav = {},
                    onRecipeItemClick = {},
                    state =
                        RandomRecipesState(
                            isLoading = false,
                            recipes = listOf(),
                            error = "",
                        ),
                )
            }
            composeTestRule.onNode(hasText(emptyMsg)).assertExists()
        }
    }

    @Test
    fun testNavigateToFavorites() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val customAppBarTag = context.getString(R.string.app_bar_tag)

        var navigateToFavoritesCalled = false

        runTest {
            composeTestRule.setContent {
                HomeContent(
                    modifier = Modifier.fillMaxSize(),
                    onNavigate = {},
                    onNavigateToFav = { navigateToFavoritesCalled = true },
                    onRecipeItemClick = {},
                    state =
                        RandomRecipesState(
                            isLoading = false,
                            recipes = listOf(),
                            error = "",
                        ),
                )
            }

            composeTestRule.onNode(hasTestTag(customAppBarTag)).performClick()

            assert(navigateToFavoritesCalled)
        }
    }
}
