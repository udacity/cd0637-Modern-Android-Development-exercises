package com.udacity.workmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class NewsSyncWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        Log.d("NewsSyncWorker", "Starting background sync...")
        
        try {
            // Simulate network operation
            delay(3000) 
            Log.d("NewsSyncWorker", "Sync completed successfully!")
            return Result.success()
        } catch (e: Exception) {
            Log.e("NewsSyncWorker", "Sync failed", e)
            return Result.retry()
        }
    }
}
