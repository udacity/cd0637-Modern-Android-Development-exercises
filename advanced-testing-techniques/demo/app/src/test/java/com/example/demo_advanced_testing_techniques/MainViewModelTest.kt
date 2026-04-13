package com.example.demo_advanced_testing_techniques

import com.example.demo_advanced_testing_techniques.data.User
import com.example.demo_advanced_testing_techniques.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MainViewModel
    private val repository = mock(UserRepository::class.java)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        `when`(repository.getUsers()).thenReturn(flowOf(emptyList()))
        viewModel = MainViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testInitialUsers() = runTest(testDispatcher) {
        val initialUsers = listOf(User("1", "Initial", "i@ex.com"))
        `when`(repository.getUsers()).thenReturn(flowOf(initialUsers))

        viewModel = MainViewModel(repository)

        // This simulates a real subscriber (like the UI), which activates WhileSubscribed
        backgroundScope.launch {
            viewModel.users.collect {}
        }

        advanceUntilIdle()

        assertEquals(initialUsers, viewModel.users.value)
    }
}
