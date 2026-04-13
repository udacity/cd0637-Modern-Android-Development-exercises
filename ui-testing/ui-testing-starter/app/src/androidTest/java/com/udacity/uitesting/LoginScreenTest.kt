package com.udacity.uitesting

import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
        // TODO: Find the username field and type "user"
        // TODO: Find the password field and type "pass"
        // TODO: Click the login button
        // TODO: Verify that "Welcome, user!" is displayed
    }

    @Test
    fun loginFailure_showsError() {
        // TODO: Find the username field and type "wrong"
        // TODO: Find the password field and type "wrong"
        // TODO: Click the login button
        // TODO: Verify that "Invalid Credentials" is displayed
    }
}
