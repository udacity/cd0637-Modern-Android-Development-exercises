package com.udacity.workmanager.worker

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.udacity.workmanager.Constants
import com.udacity.workmanager.WorkerUtils
import com.udacity.workmanager.R

// TODO: Step 1 - Extend Worker (or CoroutineWorker)
// Implement doWork()

class BlurWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext = applicationContext
        
        val resourceUri = inputData.getString(Constants.KEY_IMAGE_URI)

        return try {
            val picture = if (resourceUri != null) {
                 val resolver = appContext.contentResolver
                 BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri))
                )
            } else {
                 BitmapFactory.decodeResource(
                    appContext.resources,
                    R.drawable.mountain
                )
            }
            
            // TODO: Blur the image
            val output = WorkerUtils.blurBitmap(picture, appContext)
            val outputUri = WorkerUtils.writeBitmapToFile(appContext, output)

            // Return the output URI using the standard key
            val outputData = workDataOf(Constants.KEY_IMAGE_URI to outputUri.toString())
            Result.success(outputData)
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }
}
