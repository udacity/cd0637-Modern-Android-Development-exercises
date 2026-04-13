package com.example.data.di

import com.example.data.api.ApiService
import com.example.data.api.InternalApiService
import com.example.data.api.PublicApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @InternalApi
    @Provides
    @Singleton
    fun provideInternalApi(): ApiService = InternalApiService()

    @PublicApi
    @Provides
    @Singleton
    fun providePublicApi(): ApiService = PublicApiService()
}
