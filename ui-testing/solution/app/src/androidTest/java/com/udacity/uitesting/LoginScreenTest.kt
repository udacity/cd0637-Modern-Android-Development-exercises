package com.udacity.uitesting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun loginSuccess_navigatesToDashboard() {
        // 1. Enter valid credentials
        composeTestRule.onNodeWithTag("username_field").performTextInput("user")
        composeTestRule.onNodeWithTag("password_field").performTextInput("pass")

        // 2. Click Login
        composeTestRule.onNodeWithTag("login_button").performClick()

        // 3. Verify Navigation
        composeTestRule.onNodeWithTag("welcome_message").assertIsDisplayed()
        composeTestRule.onNodeWithTag("welcome_message").assertTextEquals("Welcome, user!")
    }

    @Test
    fun loginFailure_showsError() {
        // 1. Enter invalid credentials
        composeTestRule.onNodeWithTag("username_field").performTextInput("wrong")
        composeTestRule.onNodeWithTag("password_field").performTextInput("wrong")

        // 2. Click Login
        composeTestRule.onNodeWithTag("login_button").performClick()

        // 3. Verify Error Message
        composeTestRule.onNodeWithTag("error_message").assertIsDisplayed()
        composeTestRule.onNodeWithTag("error_message").assertTextEquals("Invalid Credentials")
    }
}
