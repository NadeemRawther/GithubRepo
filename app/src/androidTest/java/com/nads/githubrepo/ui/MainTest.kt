package com.nads.githubrepo.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.nads.githubrepo.MainActivity
import com.nads.githubrepo.ui.theme.GitHubRepoTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
@HiltAndroidTest
class MainTest {
    @get:Rule (order=2)
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity
    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)
    @Test
    fun myTest() {
        // Start the app
        composeTestRule.setContent {
            GitHubRepoTheme() {
               // GitTrendingScreen(uiState = fakeUiState, /*...*/)
            }
        }

        composeTestRule.onNodeWithText("Continue").performClick()

        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
    }
}