package com.udacity.mocking.data

import java.io.IOException

interface UserApiService {
    @Throws(IOException::class)
    fun getUsers(): List<User>
}
