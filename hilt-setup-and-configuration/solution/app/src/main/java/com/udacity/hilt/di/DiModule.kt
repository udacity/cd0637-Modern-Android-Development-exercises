package com.udacity.hilt.di

import com.udacity.hilt.data.UserRepository
import com.udacity.hilt.data.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Provides
    @Singleton
    fun provideDataSource(): String {
        return "Provided via Hilt"
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        dataSource: String
    ): UserRepository {
        return UserRepositoryImpl(dataSource)
    }
}
