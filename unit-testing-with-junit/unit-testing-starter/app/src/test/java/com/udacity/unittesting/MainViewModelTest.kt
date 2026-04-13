package com.udacity.unittesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
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

    // TODO: 1. Add InstantTaskExecutorRule to test LiveData synchronously
    // @get:Rule ...

    private lateinit var viewModel: MainViewModel
    
    // Test Dispatcher for Coroutines
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun increment_increasesCounter() {
        // TODO: 2. Call viewModel.increment()
        // TODO: 3. Assert that viewModel.counter.value is 1
    }

    @Test
    fun fetchData_updatesDataAfterDelay() = runTest(testDispatcher) {
        // TODO: 4. Call viewModel.fetchData()
        
        // TODO: 5. Verify initial state is "Loading..."
        
        // TODO: 6. Advance time by 1000ms using advanceTimeBy() or advanceUntilIdle()
        
        // TODO: 7. Verify final state is "Success!"
    }
}
