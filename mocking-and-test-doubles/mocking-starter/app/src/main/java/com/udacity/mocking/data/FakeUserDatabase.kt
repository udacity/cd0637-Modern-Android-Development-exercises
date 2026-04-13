package com.udacity.mocking.data

// TODO: Implement this Fake to store users in an in-memory MutableList
class FakeUserDatabase : UserDatabase {
    
    private val users = mutableListOf<User>()

    override fun saveUsers(users: List<User>) {
        // TODO: Clear existing and add new users
    }

    override fun getAllUsers(): List<User> {
        // TODO: Return list
        return emptyList()
    }

    override fun clear() {
        // TODO: Clear the list
    }
}
