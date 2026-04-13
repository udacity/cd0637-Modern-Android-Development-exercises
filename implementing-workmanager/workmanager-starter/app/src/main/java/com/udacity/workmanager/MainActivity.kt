package com.udacity.workmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.work.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BlurApp()
                }
            }
        }
    }
}

@Composable
fun BlurApp() {
    // TODO: Get WorkManager instance
    
    var showProgress by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Blur-O-Matic", style = MaterialTheme.typography.headlineLarge)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Just a placeholder image to represent what we are processing
        Image(
            painter = painterResource(id = R.drawable.mountain),
            contentDescription = "Image to blur",
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (showProgress) {
            CircularProgressIndicator()
            Text("Blurring in background...")
        } else {
            Button(onClick = {
                // TODO: Step 1 - Define the WorkRequest
                // Create a OneTimeWorkRequest for 'BlurWorker'.
                // val blurRequest = OneTimeWorkRequestBuilder<BlurWorker>().build()

                // TODO: Step 2 - Enqueue the Work
                // Get the WorkManager instance and call enqueue().
                // workManager.enqueue(blurRequest)

                // TODO: Step 3 - Observe the Work Status
                // We want to know when it finishes to show the result.
                // - Save the 'blurRequest.id' to the 'workId' state variable so the UI updates.
                // workId = blurRequest.id
                
                showProgress = true
            }) {
                Text("Start Blur")
            }
        }
    }
}
