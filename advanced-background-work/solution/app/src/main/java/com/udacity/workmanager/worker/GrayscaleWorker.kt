package com.udacity.workmanager.worker

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.udacity.workmanager.Constants
import com.udacity.workmanager.WorkerUtils

class GrayscaleWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext = applicationContext
        
        // Get the Input URI
        val resourceUri = inputData.getString(Constants.KEY_IMAGE_URI)

        return try {
            if (resourceUri == null) {
                Log.e("GrayscaleWorker", "Invalid input uri")
                return Result.failure()
            }
            
            val resolver = appContext.contentResolver
            val picture = BitmapFactory.decodeStream(
                resolver.openInputStream(Uri.parse(resourceUri))
            )
            
            // Simulate slow work
            Thread.sleep(3000)

            val output = WorkerUtils.grayscaleBitmap(picture)

            // Write bitmap to a temp file
            val outputUri = WorkerUtils.writeBitmapToFile(appContext, output)

            val outputData = workDataOf(Constants.KEY_IMAGE_URI to outputUri.toString())

            Result.success(outputData)
        } catch (throwable: Throwable) {
            Log.e("GrayscaleWorker", "Error applying grayscale", throwable)
            Result.failure()
        }
    }
}
