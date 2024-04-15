package com.thiagoperea.inchurchandroidchallenge.presentation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.thiagoperea.inchurchandroidchallenge.presentation.navigation.HomeRoutes
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val testRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testOpenDetailsScreenAndSaveFavorite() {
        // click on the first movie item
        testRule.waitForIdle()
        testRule.onNodeWithTag("movie_item0").performClick()

        // check if the details screen is displayed
        testRule.waitForIdle()
        testRule.onNodeWithText("Detalhes do Filme").assertExists()

        // check the details screen content
        testRule.waitForIdle()
        testRule.onNodeWithText("Movie 0").assertExists()
        testRule.onNodeWithText("2022").assertExists()
        testRule.onNodeWithText("120 minutos").assertExists()
        testRule.onNodeWithText("Action, Adventure").assertExists()
        testRule.onNodeWithText("Overview 0").assertExists()

        // click on the favorite button
        testRule.onNodeWithTag("favoriteButton").performClick()

        // click on the back button
        testRule.onNodeWithTag("backButton").performClick()

        // click on the favorites tab
        testRule.onNodeWithTag(HomeRoutes.Favorites.route).performClick()

        // check if the favorites screen is displayed
        testRule.waitForIdle()
        testRule.onNodeWithText("Favoritos").assertExists()

        // check the favorites screen content
        testRule.waitForIdle()
        testRule.onNodeWithText("Movie 0").assertExists()
        testRule.onNodeWithText("2022").assertExists()
        testRule.onNodeWithText("120 minutos").assertExists()
        testRule.onNodeWithText("Action, Adventure").assertExists()
    }
}