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
import com.udacity.workmanager.R
import androidx.core.net.toUri

class BlurWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext = applicationContext
        
        // ADDED for chaining: Read input uri
        val resourceUri = inputData.getString(Constants.KEY_IMAGE_URI)

        return try {
            val picture = if (resourceUri != null) {
                // Read from URI if provided (e.g. from a previous worker, though Blur is usually first)
                 val resolver = appContext.contentResolver
                 BitmapFactory.decodeStream(
                    resolver.openInputStream(resourceUri.toUri())
                )
            } else {
                 // Default to the mountain image
                 BitmapFactory.decodeResource(
                    appContext.resources,
                    R.drawable.mountain
                )
            }

            val output = WorkerUtils.blurBitmap(picture, appContext)

            // Write bitmap to a temp file
            val outputUri = WorkerUtils.writeBitmapToFile(appContext, output)

            // Use KEY_IMAGE_URI for chaining
            val outputData = workDataOf(Constants.KEY_IMAGE_URI to outputUri.toString())

            Result.success(outputData)
        } catch (throwable: Throwable) {
            Log.e("BlurWorker", "Error applying blur", throwable)
            Result.failure()
        }
    }
}
