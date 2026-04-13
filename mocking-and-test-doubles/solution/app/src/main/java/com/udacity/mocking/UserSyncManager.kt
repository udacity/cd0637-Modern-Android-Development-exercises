package com.udacity.mocking

import com.udacity.mocking.data.UserApiService
import com.udacity.mocking.data.UserDatabase
import java.io.IOException
import javax.inject.Inject

class UserSyncManager @Inject constructor(
    private val api: UserApiService,
    private val database: UserDatabase
) {
    /**
     * Fetches users from the API and saves them to the database.
     * If the API fails, it does nothing (does not clear the DB).
     * @return true if sync was successful, false otherwise.
     */
    fun syncUsers(): Boolean {
        return try {
            val users = api.getUsers()
            database.saveUsers(users)
            true
        } catch (e: Exception) {
            false
        }
    }
}
