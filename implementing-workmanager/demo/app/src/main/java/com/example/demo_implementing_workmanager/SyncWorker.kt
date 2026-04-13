package com.example.demo_implementing_workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class SyncWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        // Simulate a long-running sync task
        delay(5000)
        
        return Result.success()
    }
}
