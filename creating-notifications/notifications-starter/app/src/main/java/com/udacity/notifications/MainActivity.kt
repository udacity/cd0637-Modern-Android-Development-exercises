package com.udacity.notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // TODO: Step 1.1 - Create the NotificationChannel
        // NotificationUtils.createChannel(this)

        // TODO: Request Notification Permission (Required for Android 13+)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
             if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != 
                 android.content.pm.PackageManager.PERMISSION_GRANTED) {
                 requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
             }
        }
        
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotificationApp()
                }
            }
        }
    }
}

@Composable
fun NotificationApp() {
    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Notify Me!", style = MaterialTheme.typography.headlineLarge)
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Button 1: Simple Notification
        Button(onClick = {
            // TODO: Step 1.2 - Send specific notification
        }) {
            Text("Simple Notification")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button 2: Update Notification
        Button(onClick = {
            // TODO: Step 2 - Update existing notification with new text
        }) {
            Text("Update Me!")
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        // Button 3: Big Picture Style
        Button(onClick = {
            // TODO: Step 3 - Big Picture
            // 1. Decode 'mountain' drawable to Bitmap
            // 2. Call sendBigPictureNotification
        }) {
            Text("Big Picture Style")
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        // Button 4: Inbox Style
        Button(onClick = {
            // TODO: Step 4 - Inbox Style
        }) {
            Text("Inbox Style")
        }
    }
}
