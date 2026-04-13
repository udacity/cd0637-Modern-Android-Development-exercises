package com.udacity.advancedhilt.core

import android.content.Context

// TODO: Annotate with @Singleton
// TODO: Annotate constructor with @Inject and inject context
class LocalDataSource(
    private val context: Context
) : DataSource {
    override fun getData(): String {
        return "Local: ${context.packageName}"
    }
}
