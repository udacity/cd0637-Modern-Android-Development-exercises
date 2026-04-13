package com.example.demo_advanced_testing_techniques

import app.cash.turbine.test
import com.example.demo_advanced_testing_techniques.data.User
import com.example.demo_advanced_testing_techniques.data.UserDao
import com.example.demo_advanced_testing_techniques.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class FlowTurbineTest {

    @Test
    fun testUserFlowWithTurbine() = runTest {
        val userDao = mock(UserDao::class.java)
        val userList = listOf(User("1", "User 1", "u1@ex.com"))
        val userFlow = MutableStateFlow(userList)
        
        `when`(userDao.getAllUsers()).thenReturn(userFlow.asStateFlow())
        
        val repository = UserRepository(userDao)
        
        repository.getUsers().test {
            val item1 = awaitItem()
            assertEquals(userList, item1)
            
            val newList = userList + User("2", "User 2", "u2@ex.com")
            userFlow.value = newList
            
            val item2 = awaitItem()
            assertEquals(newList, item2)
        }
    }
}
