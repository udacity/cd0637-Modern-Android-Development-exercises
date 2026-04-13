package com.example.demo_creating_notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

object NotificationHelper {

    const val CHANNEL_ID = "demo-notification-channel"
    const val CHANNEL_NAME = "Demo Notifications"
    const val FOCUS_NOTIFICATION_ID = 101
    const val SECURITY_NOTIFICATION_ID = 102

    /**
     * Creates a notification channel (required for API 26+)
     */
    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Used for demonstrating various notification types"
                enableLights(true)
                enableVibration(true)
            }

            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    /**
     * Sends a simple notification for the "Focus Timer" use case
     */
    fun sendFocusNotification(context: Context) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Time for a break!")
            .setContentText("You've been focused for 10 seconds. Great job!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.notify(FOCUS_NOTIFICATION_ID, builder.build())
    }

    /**
     * Sends a high-priority notification for the "Security Alert" use case
     */
    fun sendSecurityNotification(context: Context) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("Security Alert")
            .setContentText("New login detected from a Chrome on Mac. Was this you?")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.notify(SECURITY_NOTIFICATION_ID, builder.build())
    }
}
