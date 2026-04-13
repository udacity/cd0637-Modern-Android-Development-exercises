package com.udacity.workmanager.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

// TODO: Step 1 - Extend CoroutineWorker
// Implement doWork()
// Add a small delay to simulate network work
// Return Result.success()

class NewsSyncWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        // TODO: Simulate data sync
        // 1. Log that work started
        // 2. delay(2000)
        // 3. Log that work finished
        return Result.success()
    }
}
