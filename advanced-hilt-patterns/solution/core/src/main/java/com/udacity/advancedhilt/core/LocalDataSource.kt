package com.udacity.advancedhilt.core

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) : DataSource {
    override fun getData(): String {
        return "Local: ${context.packageName}"
    }
}
