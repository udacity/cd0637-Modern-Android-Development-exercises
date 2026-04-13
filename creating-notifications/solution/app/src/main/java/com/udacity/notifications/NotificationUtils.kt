package com.udacity.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

object NotificationUtils {

    const val CHANNEL_ID = "notify-me-channel"
    const val CHANNEL_NAME = "Notify Me! News"
    const val NOTIFICATION_ID = 1

    fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
                .apply {
                    setShowBadge(false)
                    enableLights(true)
                    enableVibration(true)
                    description = "News and Updates from Notify Me!"
                }

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    fun sendNotification(context: Context, messageBody: String) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)

        // Create an Intent for the activity you want to start
        val contentIntent = Intent(context, MainActivity::class.java)
        
        // Create the TaskStackBuilder (optional but good practice)
        val contentPendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Use default icon or mountain
            .setContentTitle("Notify Me!")
            .setContentText(messageBody)
            .setContentIntent(contentPendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    fun sendBigPictureNotification(context: Context, bitmap: android.graphics.Bitmap) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Big Picture Notification")
            .setContentText("Check out this image!")
            .setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(bitmap)
                .bigLargeIcon(null as android.graphics.Bitmap?))
            .setAutoCancel(true)

        notificationManager.notify(NOTIFICATION_ID + 1, builder.build())
    }
    
    fun sendInboxStyleNotification(context: Context) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("5 New Messages")
            .setContentText("You have new emails")
            .setStyle(NotificationCompat.InboxStyle()
                .addLine("Check this out")
                .addLine("Meeting at 3 PM")
                .addLine("Lunch?")
                .addLine("Udacity Review")
                .setBigContentTitle("5 New Messages")
                .setSummaryText("kidus@example.com"))
            .setAutoCancel(true)

        notificationManager.notify(NOTIFICATION_ID + 2, builder.build())
    }
}
