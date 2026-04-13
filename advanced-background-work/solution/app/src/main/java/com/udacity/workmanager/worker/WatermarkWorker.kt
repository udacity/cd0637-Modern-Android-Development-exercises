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

class WatermarkWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext = applicationContext
        
        val resourceUri = inputData.getString(Constants.KEY_IMAGE_URI)

        return try {
             if (resourceUri == null) {
                Log.e("WatermarkWorker", "Invalid input uri")
                return Result.failure()
            }
            
            val resolver = appContext.contentResolver
            val picture = BitmapFactory.decodeStream(
                resolver.openInputStream(Uri.parse(resourceUri))
            )
            
            // Simulate slow work
            Thread.sleep(3000)

            val output = WorkerUtils.watermarkBitmap(picture, appContext)

            // Write bitmap to a temp file
            val outputUri = WorkerUtils.writeBitmapToFile(appContext, output)

            // Pass it on
            val outputData = workDataOf(Constants.KEY_IMAGE_URI to outputUri.toString())

            Result.success(outputData)
        } catch (throwable: Throwable) {
            Log.e("WatermarkWorker", "Error applying watermark", throwable)
            Result.failure()
        }
    }
}
