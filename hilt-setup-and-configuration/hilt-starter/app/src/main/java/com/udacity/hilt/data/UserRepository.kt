package com.udacity.hilt.data

import com.udacity.hilt.domain.User
import kotlinx.coroutines.delay

interface UserRepository {
    suspend fun getUser(): User
}

// TODO: In the solution, this will be provided by Hilt
class UserRepositoryImpl(
    private val dataSource: String
) : UserRepository {
    override suspend fun getUser(): User {
        delay(1000) // Simulate network delay
        return User(
            name = "Test User",
            email = "test.user@example.com",
            role = "Admin ($dataSource)"
        )
    }
}
