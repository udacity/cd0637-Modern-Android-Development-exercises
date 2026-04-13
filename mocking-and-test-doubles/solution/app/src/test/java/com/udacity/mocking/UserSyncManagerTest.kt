package com.udacity.mocking

import com.udacity.mocking.data.FakeUserDatabase
import com.udacity.mocking.data.User
import com.udacity.mocking.data.UserApiService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class UserSyncManagerTest {

    private lateinit var fakeDatabase: FakeUserDatabase
    private lateinit var mockApi: UserApiService
    private lateinit var syncManager: UserSyncManager

    @Before
    fun setup() {
        fakeDatabase = FakeUserDatabase()
        mockApi = mockk<UserApiService>()
        syncManager = UserSyncManager(mockApi, fakeDatabase)
    }

    @Test
    fun syncSuccess_savesUsersToDatabase() {
        val users = listOf(User("1", "Alice"), User("2", "Bob"))
        // Stub
        every { mockApi.getUsers() } returns users

        // Act
        val result = syncManager.syncUsers()

        // Assert
        assertTrue(result)
        assertEquals(2, fakeDatabase.getAllUsers().size)
        assertEquals("Alice", fakeDatabase.getAllUsers()[0].name)
        
        // Verify interaction
        verify(exactly = 1) { mockApi.getUsers() }
    }

    @Test
    fun syncFailure_doesNotSaveUsers() {
        // Stub
        every { mockApi.getUsers() } throws IOException("Network error")

        // Act
        val result = syncManager.syncUsers()

        // Assert
        assertFalse(result)
        assertTrue(fakeDatabase.getAllUsers().isEmpty())
        
        // Verify interaction
        verify(exactly = 1) { mockApi.getUsers() }
    }
}
