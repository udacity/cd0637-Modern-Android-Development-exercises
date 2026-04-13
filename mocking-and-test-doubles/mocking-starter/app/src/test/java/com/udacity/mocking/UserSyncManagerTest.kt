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
        // TODO: 1. Stub mockApi.getUsers() to return a list of users (User("1", "Alice"))
        // TODO: 2. Call syncManager.syncUsers()
        // TODO: 3. Verify the result is true
        // TODO: 4. Assert that fakeDatabase.getAllUsers() contains the user
    }

    @Test
    fun syncFailure_doesNotSaveUsers() {
        // TODO: 1. Stub mockApi.getUsers() to throw an IOException
        // TODO: 2. Call syncManager.syncUsers()
        // TODO: 3. Verify the result is false
        // TODO: 4. Assert that fakeDatabase.getAllUsers() is empty
    }
}
