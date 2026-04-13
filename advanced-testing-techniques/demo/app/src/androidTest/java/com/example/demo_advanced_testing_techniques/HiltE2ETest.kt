package com.example.demo_advanced_testing_techniques

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HiltE2ETest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testAppLaunchAndUI() {
        // Verify title is displayed
        composeRule.onNodeWithText("Advanced Testing Demo").assertExists()
        
        // In a real E2E, we would interact with the "Add User" button
        // and verify the list updates.
    }
}
