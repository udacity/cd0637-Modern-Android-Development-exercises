package com.example.demo_advanced_testing_techniques.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.demo_advanced_testing_techniques.data.User
import com.example.demo_advanced_testing_techniques.data.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import androidx.hilt.work.HiltWorker

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: UserRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            Log.d("SyncWorker", "Starting sync...")
            // Simulate sync by adding a system user if empty
            val newUser = User("system_user", "System", "system@example.com")
            repository.addUser(newUser)
            Log.d("SyncWorker", "Sync successful")
            Result.success()
        } catch (e: Exception) {
            Log.e("SyncWorker", "Sync failed", e)
            Result.failure()
        }
    }
}
