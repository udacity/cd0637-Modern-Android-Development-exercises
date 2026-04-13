package com.example.demo_creating_notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.demo_creating_notifications.ui.theme.Demo02Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Create Channel on startup
        NotificationHelper.createNotificationChannel(this)

        // Request Notification Permission (Android 13+)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            val permissionLauncher = registerForActivityResult(
                androidx.activity.result.contract.ActivityResultContracts.RequestPermission()
            ) { /* Handle permission result if needed */ }
            
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != 
                android.content.pm.PackageManager.PERMISSION_GRANTED) {
                permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        enableEdgeToEdge()
        setContent {
            Demo02Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotificationDemoScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationDemoScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Udacity Notification Demo",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = Bold,
            textAlign = TextAlign.Center
        )
        
        Text(
            text = "Explore how notifications provide value to users both in the background and for urgent alerts.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(Modifier.height(48.dp))

        // Use Case 1: Focus Timer
        Button(
            onClick = {
                scope.launch {
                    // Simulate a short focus session for the demo
                    delay(10000)
                    NotificationHelper.sendFocusNotification(context)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start 10s Focus Session")
        }
        
        Text(
            text = "Simulates a background notification. Start the timer and press Home!",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(Modifier.height(24.dp))

        // Use Case 2: Security Alert
        Button(
            onClick = {
                NotificationHelper.sendSecurityNotification(context)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Simulate Security Alert")
        }
        
        Text(
            text = "Demonstrates urgent, high-priority notifications.",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}