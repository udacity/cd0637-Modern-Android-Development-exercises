package com.example.demo_ui_testing

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * LoginScreenTest demonstrates how to test Compose UIs.
 * Includes tests for user input, button interactions, async loading, 
 * navigation success, and error state verification.
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun verifyInitialUIState() {
        // Verify displayed data (labels and initial state)
        composeTestRule.onNodeWithText("Member Login").assertIsDisplayed()
        composeTestRule.onNodeWithTag("username_field").assertIsDisplayed()
        composeTestRule.onNodeWithTag("password_field").assertIsDisplayed()
        composeTestRule.onNodeWithTag("login_button").assertIsNotEnabled()
    }

    @Test
    fun loginSuccess_navigatesToDashboard() {
        // 1. Enter valid credentials
        composeTestRule.onNodeWithTag("username_field").performTextInput("admin")
        composeTestRule.onNodeWithTag("password_field").performTextInput("password123")
        composeTestRule.onNodeWithTag("login_button").assertIsEnabled()

        // 2. Click Login
        composeTestRule.onNodeWithTag("login_button").performClick()

        // 3. Verify Asynchronous Loading State
        composeTestRule.onNodeWithTag("loading_indicator").assertIsDisplayed()
        composeTestRule.onNodeWithText("Authenticating...").assertIsDisplayed()

        // 4. Handle Async Operation: Wait for navigation to Dashboard
        // We wait up to 5 seconds for the welcome message to appear (simulated 2s delay)
        composeTestRule.waitUntil(5000) {
            composeTestRule.onAllNodesWithTag("welcome_message").fetchSemanticsNodes().isNotEmpty()
        }

        // 5. Verify Navigation Flow & Displayed Data
        composeTestRule.onNodeWithTag("welcome_message").assertIsDisplayed()
        composeTestRule.onNodeWithText("Logged in as: admin").assertIsDisplayed()
    }

    @Test
    fun loginFailure_showsErrorState() {
        // 1. Enter invalid credentials
        composeTestRule.onNodeWithTag("username_field").performTextInput("wrong_user")
        composeTestRule.onNodeWithTag("password_field").performTextInput("wrong_pass")

        // 2. Click Login
        composeTestRule.onNodeWithTag("login_button").performClick()

        // 3. Wait for async failure
        composeTestRule.waitUntil(5000) {
            composeTestRule.onAllNodesWithTag("error_message").fetchSemanticsNodes().isNotEmpty()
        }

        // 4. Verify Error State
        composeTestRule.onNodeWithTag("error_message").assertIsDisplayed()
        composeTestRule.onNodeWithText("Invalid Credentials").assertIsDisplayed()
    }

    @Test
    fun emptyFields_keepsLoginButtonDisabled() {
        // Verify user interaction constraint
        composeTestRule.onNodeWithTag("username_field").performTextInput("admin")
        composeTestRule.onNodeWithTag("login_button").assertIsNotEnabled()
        
        composeTestRule.onNodeWithTag("username_field").performTextClearance()
        composeTestRule.onNodeWithTag("password_field").performTextInput("password123")
        composeTestRule.onNodeWithTag("login_button").assertIsNotEnabled()
    }
}
