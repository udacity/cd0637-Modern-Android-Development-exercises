package com.example.demo_hilt_setup_and_configuration.di

import com.example.demo_hilt_setup_and_configuration.data.UserRepository
import com.example.demo_hilt_setup_and_configuration.data.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataSourceName(): String {
        return "Provided via Hilt @Module"
    }

    @Provides
    @Singleton
    fun provideUserRepository(dataSource: String): UserRepository {
        return UserRepositoryImpl(dataSource)
    }
}
