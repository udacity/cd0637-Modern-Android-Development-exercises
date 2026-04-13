package com.example.demo_advanced_hilt_patterns

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import com.example.demo_advanced_hilt_patterns.util.AppLogger

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HiltInstrumentationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var logger: AppLogger

    @Test
    fun testDependencyInjection() {
        hiltRule.inject()
        assertTrue(::logger.isInitialized)
        logger.log("Integration test injection successful!")
    }
}
