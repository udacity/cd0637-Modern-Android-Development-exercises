# Exercise 11: Notifications - "Notify Me!"

## Learning Objective
Learn to create, update, and customize Notifications in Android.
You will implement Notification Channels, Simple Notifications, BigPicture Style, and Inbox Style.

## Overview
"Notify Me!" is a dashboard app that lets you trigger different types of notifications to test them out.
Currently, the buttons do nothing. Your job is to make them work!

## Instructions

### Step 1: Create a Notification Channel
Open `NotificationUtils.kt`.
1.  Implement `createChannel(context)`.
2.  Use `NotificationChannel` (API 26+) to define a channel with ID `notify-me-channel`.
3.  Register it with `NotificationManager`.
4.  **Call this method** in `MainActivity.kt` -> `onCreate`.

### Step 2: Send a Simple Notification
Open `NotificationUtils.kt`.
1.  Implement `sendNotification`.
2.  Use `NotificationCompat.Builder`.
3.  Set:
    *   Small Icon: `R.drawable.ic_launcher_foreground`
    *   Title: "Notify Me!"
    *   Text: `messageBody`
    *   Priority: `PRIORITY_HIGH`
    *   **PendingIntent**: Create an Intent to open `MainActivity` when clicked.
4.  **Connect it**: In `MainActivity.kt`, call this function when the "Simple Notification" button is clicked.

### Step 3: Update a Notification
Open `MainActivity.kt`.
1.  In the "Update Me!" button listener, call `sendNotification` again with a *different text*.
2.  Because we use the same `NOTIFICATION_ID` (you should define one), it will update the existing notification instead of creating a new one.

### Step 4: Big Picture Style
Open `NotificationUtils.kt`.
1.  Create a new function `sendBigPictureNotification(context, bitmap)`.
2.  Use `.setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))`.
3.  **Connect it**: in `MainActivity`, decode a resource (e.g. `R.drawable.ic_launcher_background`) and pass the bitmap to this function.

### Step 5: Inbox Style
Open `NotificationUtils.kt`.
1.  Create a new function `sendInboxStyleNotification`.
2.  Use `.setStyle(NotificationCompat.InboxStyle().addLine(...))`.
3.  Add 3-5 dummy lines (e.g., "Email 1", "Meeting at 2pm", etc.).
4.  **Connect it**: Call this from the "Inbox Style" button.

## Run the App
Test all 4 buttons and verify the notifications appear and behave as expected!
