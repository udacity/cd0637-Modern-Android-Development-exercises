package com.udacity.workmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import coil.compose.AsyncImage
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.work.*
import com.udacity.workmanager.worker.BlurWorker

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
    val context = LocalContext.current
    val workManager = WorkManager.getInstance(context)
    
    // We strive to only have one blur status to observe in this simple UI
    var workId by remember { mutableStateOf<java.util.UUID?>(null) }
    
    // Observe WorkInfo if we have an ID
    val workInfo = if (workId != null) {
        workManager.getWorkInfoByIdLiveData(workId!!).observeAsState().value
    } else {
        null
    }

    val isWorkRunning = workInfo?.state == WorkInfo.State.RUNNING || workInfo?.state == WorkInfo.State.ENQUEUED
    val isWorkFinished = workInfo?.state?.isFinished == true

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Blur-O-Matic", style = MaterialTheme.typography.headlineLarge)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Show Output if available, otherwise show default
        val outputUri = if (isWorkFinished && !workInfo?.outputData?.getString("KEY_OUTPUT_URI").isNullOrEmpty()) {
             workInfo?.outputData?.getString("KEY_OUTPUT_URI")
        } else null

        if (outputUri != null) {
            AsyncImage(
                model = outputUri,
                contentDescription = "Blurred Image",
                modifier = Modifier.size(300.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.mountain),
                contentDescription = "Image to blur",
                modifier = Modifier.size(300.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (isWorkRunning) {
            CircularProgressIndicator()
            Text("Blurring in background...", modifier = Modifier.padding(top=8.dp))
        } else if (isWorkFinished) {
            val isSuccess = workInfo?.state == WorkInfo.State.SUCCEEDED
            Text(
                if (isSuccess) "Blur Complete!" else "Blur Failed!", 
                style = MaterialTheme.typography.titleLarge, 
                color = if (isSuccess) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        } else {
            Button(onClick = {
                // Create Request
                val blurRequest = OneTimeWorkRequestBuilder<BlurWorker>()
                    .build()
                
                // Enqueue
                workManager.enqueue(blurRequest)
                
                // Track ID to observe
                workId = blurRequest.id
            }) {
                Text("Start Blur")
            }
        }
    }
}
