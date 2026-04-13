# Exercise 11: Notifications - Solution

## Completed: "Notify Me!"
This project contains the **completed** solution for the Notifications exercise.

## Features Implemented
1.  **Notification Channels**:
    *   Changes created in `NotificationUtils.createChannel`.
    *   Channel ID `notify-me-channel`.
2.  **Simple Notification**:
    *   Sends a basic title/text notification with a high priority.
    *   Includes a `PendingIntent` to open the app on click.
3.  **Update Notification**:
    *   Updates the existing notification (ID 1) by sending a new payload with the same ID.
4.  **Big Picture Style**:
    *   Uses `NotificationCompat.BigPictureStyle` to show an expanded image.
5.  **Inbox Style**:
    *   Uses `NotificationCompat.InboxStyle` to show multiple lines of text.

## How to Test
1.  Run the app.
2.  Click **Simple Notification**: Verify it appears in the status bar.
3.  Click **Update Me!**: Verify the text changes.
4.  Click **Big Picture**: Expand the notification to see the image.
5.  Click **Inbox Style**: Expand to see the list of dummy messages.
