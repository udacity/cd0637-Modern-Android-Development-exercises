package com.udacity.workmanager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.annotation.DrawableRes
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.FileNotFoundException
import java.util.UUID

// Helper methods for the Blur-O-Matic exercise
// Students don't need to modify this file, it just handles the "heavy lifting" 
// of file IO and Bitmap manipulation so they can focus on WorkManager.

object WorkerUtils {
    private const val TAG = "WorkerUtils"

    @Throws(FileNotFoundException::class)
    fun writeBitmapToFile(applicationContext: Context, bitmap: Bitmap): Uri {
        val name = String.format("blur-filter-output-%s.png", UUID.randomUUID().toString())
        val outputDir = File(applicationContext.filesDir, "blur_outputs")
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }
        val outputFile = File(outputDir, name)
        var out: FileOutputStream? = null
        try {
            out = FileOutputStream(outputFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* ignored for PNG */, out)
        } finally {
            out?.let {
                try {
                    it.close()
                } catch (ignore: IOException) {
                }
            }
        }
        return Uri.fromFile(outputFile)
    }

    fun makeStatusNotification(message: String, context: Context) {
        // Implementation omitted for brevity in this exercise, 
        // simplifies dealing with Notification Channels permissions.
        Log.i(TAG, "Notification: $message")
    }

    fun blurBitmap(bitmap: Bitmap, applicationContext: Context): Bitmap {
        // Simulate heavy processing
        try {
            Thread.sleep(10000)
        } catch (e: InterruptedException) {
            Log.e(TAG, "Thread sleep failed", e)
        }

        // VISUAL BLUR IMPLEMENTATION:
        val width = Math.round(bitmap.width * 0.01f)
        val height = Math.round(bitmap.height * 0.01f)

        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false)
        return Bitmap.createScaledBitmap(scaledBitmap, bitmap.width, bitmap.height, true)
    }

    fun grayscaleBitmap(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        
        val canvas = android.graphics.Canvas(outputBitmap)
        val paint = android.graphics.Paint()
        val colorMatrix = android.graphics.ColorMatrix()
        colorMatrix.setSaturation(0f)
        val filter = android.graphics.ColorMatrixColorFilter(colorMatrix)
        paint.colorFilter = filter
        
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return outputBitmap
    }

    fun watermarkBitmap(bitmap: Bitmap, applicationContext: Context): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        
        val canvas = android.graphics.Canvas(outputBitmap)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        
        val paint = android.graphics.Paint()
        paint.color = android.graphics.Color.RED
        paint.textSize = 90f
        paint.isAntiAlias = true
        paint.alpha = 150
        
        val text = "UDACITY"
        val textWidth = paint.measureText(text)
        canvas.drawText(text, width - textWidth - 20f, height - 20f, paint)
        
        return outputBitmap
    }
}
