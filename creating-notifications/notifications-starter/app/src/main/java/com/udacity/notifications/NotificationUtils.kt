package com.udacity.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

/**
 * Helper class to manage notification channels and sending notifications.
 */
object NotificationUtils {

    const val CHANNEL_ID = "notify-me-channel"
    const val CHANNEL_NAME = "Notify Me! News"
    const val NOTIFICATION_ID = 1

    // TODO: Step 1.1 - Create the NotificationChannel
    fun createChannel(context: Context) {
        // 1. Check if Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        // 2. Create NotificationChannel object
        // 3. Configure settings (vibration, description, etc.)
        // 4. Get the NotificationManager and create the channel
    }

    // TODO: Step 1.2 - Create and Send a Simple Notification
    fun sendNotification(context: Context, messageBody: String) {
        // 1. Get NotificationManager
        // 2. Create an Intent and PendingIntent to open MainActivity
        // 3. Build the Notification using NotificationCompat.Builder
        // 4. Send the notification using notify()
    }

    // TODO: Step 3 - BigPicture Style
    fun sendBigPictureNotification(context: Context, bitmap: android.graphics.Bitmap) {
        // 1. Get NotificationManager
        // 2. Build the Notification with BigPictureStyle
        // 3. Notify (Use a different ID)
    }
    
    // TODO: Step 4 - Inbox Style
    fun sendInboxStyleNotification(context: Context) {
        // 1. Get NotificationManager
        // 2. Build the Notification with InboxStyle
        // 3. Notify (Use a different ID)
    }
}
