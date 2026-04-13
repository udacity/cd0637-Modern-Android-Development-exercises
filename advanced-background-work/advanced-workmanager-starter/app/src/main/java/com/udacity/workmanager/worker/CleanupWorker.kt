package com.udacity.workmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File

class CleanupWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext = applicationContext

        return try {
            val outputDirectory = File(appContext.filesDir, "blur_outputs")
            if (outputDirectory.exists()) {
                val entries = outputDirectory.listFiles()
                if (entries != null) {
                    for (entry in entries) {
                        val name = entry.name
                        if (name.isNotEmpty() && name.endsWith(".png")) {
                            val deleted = entry.delete()
                            Log.i("CleanupWorker", "Deleted $name - $deleted")
                        }
                    }
                }
            }
            Result.success()
        } catch (exception: Exception) {
            Log.e("CleanupWorker", "Error cleaning up", exception)
            Result.failure()
        }
    }
}
