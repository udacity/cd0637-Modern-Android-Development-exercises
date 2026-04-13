package com.example.mocking

import com.example.mocking.data.FakeUserDatabase
import com.example.mocking.data.User
import com.example.mocking.data.UserApiService
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
        // Stub: Define what the mock should return
        every { mockApi.getUsers() } returns users

        // Act: Call the method under test
        val result = syncManager.syncUsers()

        // Assert: Check the state of the fake database
        assertTrue(result)
        assertEquals(2, fakeDatabase.getAllUsers().size)
        assertEquals("Alice", fakeDatabase.getAllUsers()[0].name)
        
        // Verify: Check if the mock method was actually called
        verify(exactly = 1) { mockApi.getUsers() }
    }

    @Test
    fun syncFailure_doesNotSaveUsers() {
        // Stub: Define the mock to throw an exception
        every { mockApi.getUsers() } throws IOException("Network error")

        // Act
        val result = syncManager.syncUsers()

        // Assert: Check that sync failed and database is empty
        assertFalse(result)
        assertTrue(fakeDatabase.getAllUsers().isEmpty())
        
        // Verify interaction
        verify(exactly = 1) { mockApi.getUsers() }
    }
}
