package com.example.demo_hilt_setup_and_configuration.data

data class User(val name: String, val role: String, val bio: String)

interface UserRepository {
    fun getUser(): User
}

class UserRepositoryImpl(private val dataSource: String) : UserRepository {
    override fun getUser(): User {
        return User(
            name = "Injected John",
            role = "Hilt Component",
            bio = "I was $dataSource"
        )
    }
}
