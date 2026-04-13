package com.example.mocking

import com.example.mocking.data.UserApiService
import com.example.mocking.data.UserDatabase

class UserSyncManager constructor(
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
