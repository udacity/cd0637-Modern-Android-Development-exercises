package com.udacity.advancedtesting.di

import android.content.Context
import androidx.room.Room
import com.udacity.advancedtesting.data.AppDatabase
import com.udacity.advancedtesting.data.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "tasks.db").build()
    }

    @Provides
    fun provideTaskDao(db: AppDatabase): TaskDao = db.taskDao()
}
