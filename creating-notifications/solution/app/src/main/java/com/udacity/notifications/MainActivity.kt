package com.udacity.notifications

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.udacity.notifications.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 1. Create Channel on startup
        NotificationUtils.createChannel(this)

        // 2. Request Notification Permission (Android 13+)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            val permissionLauncher = registerForActivityResult(
                androidx.activity.result.contract.ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {
                    // Permission granted
                }
            }
            
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != 
                android.content.pm.PackageManager.PERMISSION_GRANTED) {
                permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
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
            NotificationUtils.sendNotification(context, "This is a standard notification.")
        }) {
            Text("Simple Notification")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button 2: Update Notification
        Button(onClick = {
             NotificationUtils.sendNotification(context, "Updated! The ID is the same.")
        }) {
            Text("Update Me!")
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        // Button 3: Big Picture Style
        Button(onClick = {
            // Use the mountain image for the Big Picture style
            val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.mountain)
            NotificationUtils.sendBigPictureNotification(context, bitmap)
        }) {
            Text("Big Picture Style")
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        // Button 4: Inbox Style
        Button(onClick = {
            NotificationUtils.sendInboxStyleNotification(context)
        }) {
            Text("Inbox Style")
        }
    }
}
