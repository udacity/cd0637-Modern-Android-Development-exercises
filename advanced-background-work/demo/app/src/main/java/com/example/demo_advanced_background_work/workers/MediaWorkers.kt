package com.example.demo_advanced_background_work.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.example.demo_advanced_background_work.data.MediaRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay

@HiltWorker
class DownloadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: MediaRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        repository.addLog("Starting Download...")
        delay(2000)
        repository.addLog("Download Complete.")
        return Result.success()
    }
}

@HiltWorker
class BlurFilterWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: MediaRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        repository.addLog("Applying Blur Filter...")
        delay(3000)
        repository.addLog("Blur Filter Applied.")
        return Result.success()
    }
}

@HiltWorker
class GrayscaleFilterWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: MediaRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        repository.addLog("Applying Grayscale Filter...")
        delay(1500)
        repository.addLog("Grayscale Filter Applied.")
        return Result.success()
    }
}

@HiltWorker
class UploadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: MediaRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        repository.addLog("Uploading Final Result...")
        delay(2000)
        repository.addLog("Upload Successful! 🎉")
        return Result.success()
    }
}
