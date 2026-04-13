package com.example.data.api

interface ApiService {
    fun fetchData(): String
}

class InternalApiService : ApiService {
    override fun fetchData() = "Internal Data from Multi-module"
}

class PublicApiService : ApiService {
    override fun fetchData() = "Public Data from Multi-module"
}
