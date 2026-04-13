package com.example.data.repository

import com.example.data.api.ApiService
import com.example.data.di.PublicApi
import javax.inject.Inject
import javax.inject.Singleton

interface DataRepository {
    fun getMessage(): String
}

@Singleton
class DataRepositoryImpl @Inject constructor(
    @PublicApi private val apiService: ApiService
) : DataRepository {
    override fun getMessage() = "Repository says: ${apiService.fetchData()}"
}
