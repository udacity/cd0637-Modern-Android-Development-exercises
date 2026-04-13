package com.example.mocking.data

class FakeUserDatabase : UserDatabase {
    
    private val users = mutableListOf<User>()

    override fun saveUsers(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
    }

    override fun getAllUsers(): List<User> {
        return users.toList()
    }

    override fun clear() {
        users.clear()
    }
}
