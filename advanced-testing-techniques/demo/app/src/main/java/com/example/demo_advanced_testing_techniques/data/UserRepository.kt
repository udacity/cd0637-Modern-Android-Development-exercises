package com.example.demo_advanced_testing_techniques.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun getUsers(): Flow<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUser(id: String): User? = userDao.getUserById(id)
}
