package com.udacity.mocking.data

interface UserDatabase {
    fun saveUsers(users: List<User>)
    fun getAllUsers(): List<User>
    fun clear()
}
