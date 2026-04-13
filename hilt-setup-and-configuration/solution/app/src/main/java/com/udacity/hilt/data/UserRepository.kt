package com.udacity.hilt.data

import com.udacity.hilt.domain.User
import kotlinx.coroutines.delay
import javax.inject.Inject

interface UserRepository {
    suspend fun getUser(): User
}

class UserRepositoryImpl @Inject constructor(
    private val dataSource: String
) : UserRepository {
    override suspend fun getUser(): User {
        delay(1000)
        return User(
            name = "Hilt User",
            email = "hilt.user@example.com",
            role = "Premium ($dataSource)"
        )
    }
}
