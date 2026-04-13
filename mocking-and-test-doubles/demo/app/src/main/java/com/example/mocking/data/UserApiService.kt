package com.example.mocking.data

import java.io.IOException

interface UserApiService {
    @Throws(IOException::class)
    fun getUsers(): List<User>
}
