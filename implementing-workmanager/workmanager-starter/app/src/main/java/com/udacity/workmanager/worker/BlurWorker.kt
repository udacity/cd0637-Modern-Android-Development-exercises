package com.udacity.workmanager.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.udacity.workmanager.WorkerUtils

// TODO: Step 1 - Extend Worker (or CoroutineWorker)
// Implement doWork()

class BlurWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext = applicationContext

        // TODO: Step 2 - Get Input Data
        // val resourceUri = inputData.getString("KEY_IMAGE_URI")

        return try {
            // TODO: Step 2 - Decode the input image
            // In a real app, you would get the URI from 'inputData'.
            // For this exercise, we will just use the included 'mountain' image resource.
            // UNCOMMENT the following code:
            
            /*
            val picture = BitmapFactory.decodeResource(
                applicationContext.resources,
                R.drawable.mountain
            )
            */

            // TODO: Step 3 - Blur and Save the image
            // We have provided a helper class 'WorkerUtils' to do the heavy lifting.
            // - Call WorkerUtils.blurBitmap(...) passing the picture and applicationContext
            // - Call WorkerUtils.writeBitmapToFile(...) passing the context and the blurred bitmap
            // - This returns the Uri of the saved file.
            
            // UNCOMMENT and IMPLEMENT:
            /*
            val output = WorkerUtils.blurBitmap(picture, applicationContext)
            val outputUri = WorkerUtils.writeBitmapToFile(applicationContext, output)
            */

            // TODO: Step 4 - Return the Output Data
            // We need to pass the 'outputUri' string back to the UI.
            // - Create a Data object: workDataOf("KEY_OUTPUT_URI" to outputUri.toString())
            // - Return Result.success(outputData)
            
            return Result.failure() // CHANGE THIS to Result.success(...)
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }
}
