package com.udacity.unittesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule =  InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        // Set Main dispatcher to test dispatcher for Coroutines
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun increment_increasesCounter() {
        viewModel.increment()
        assertEquals(1, viewModel.counter.value)
    }

    @Test
    fun fetchData_updatesDataAfterDelay() = runTest(testDispatcher) {
        viewModel.fetchData()
        
        // Advance only enough to verify Loading
        // (With StandardTestDispatcher, coroutines don't run until yielded/advanced)
        testScheduler.runCurrent() 
        assertEquals("Loading...", viewModel.data.value)
        
        // Advance past delay
        advanceUntilIdle()
        assertEquals("Success!", viewModel.data.value)
    }
}
