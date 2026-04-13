package com.udacity.workmanager.worker

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.udacity.workmanager.WorkerUtils
import com.udacity.workmanager.R

class BlurWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext = applicationContext

        return try {
            // Ideally we'd read from inputData. For simplicity in this demo, 
            // if no input is provided we use a default resource.
            // In a real app, reading InputData is crucial.
            
            // Simulating reading an image. 
            val picture = BitmapFactory.decodeResource(
                appContext.resources,
                R.drawable.mountain
            )

            val output = WorkerUtils.blurBitmap(picture, appContext)

            // Write bitmap to a temp file
            val outputUri = WorkerUtils.writeBitmapToFile(appContext, output)

            val outputData = workDataOf("KEY_OUTPUT_URI" to outputUri.toString())

            Result.success(outputData)
        } catch (throwable: Throwable) {
            Log.e("BlurWorker", "Error applying blur", throwable)
            Result.failure()
        }
    }
}
