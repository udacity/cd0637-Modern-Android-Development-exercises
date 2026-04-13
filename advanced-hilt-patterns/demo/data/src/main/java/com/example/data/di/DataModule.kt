package com.example.data.di

import com.example.data.repository.DataRepository
import com.example.data.repository.DataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindDataRepository(
        dataRepositoryImpl: DataRepositoryImpl
    ): DataRepository
}
