package com.udacity.workmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.*
import com.udacity.workmanager.Constants
import com.udacity.workmanager.worker.BlurWorker
import com.udacity.workmanager.worker.CleanupWorker
import com.udacity.workmanager.worker.GrayscaleWorker
import com.udacity.workmanager.worker.WatermarkWorker

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
    val context = androidx.compose.ui.platform.LocalContext.current
    val workManager = WorkManager.getInstance(context)
    
    // State to track the work ID
    var workId by remember { mutableStateOf<java.util.UUID?>(null) }
    
    // Observe the work info
    val workInfo = if (workId != null) {
        workManager.getWorkInfoByIdLiveData(workId!!).observeAsState().value
    } else {
        null
    }
    
    val isWorkRunning = workInfo?.state == WorkInfo.State.RUNNING || 
                        workInfo?.state == WorkInfo.State.ENQUEUED || 
                        workInfo?.state == WorkInfo.State.BLOCKED
                        
    val isWorkFinished = workInfo?.state?.isFinished == true

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Blur-O-Matic", style = MaterialTheme.typography.headlineLarge)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Show Output if available, otherwise show default
        val outputUri = if (isWorkFinished && !workInfo?.outputData?.getString(Constants.KEY_IMAGE_URI).isNullOrEmpty()) {
             workInfo?.outputData?.getString(Constants.KEY_IMAGE_URI)
        } else null

        if (outputUri != null) {
            coil.compose.AsyncImage(
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
            Text("Processing Pipeline...", modifier = Modifier.padding(top=8.dp))
        } else if (isWorkFinished) {
            val isSuccess = workInfo?.state == WorkInfo.State.SUCCEEDED
            Text(
                if (isSuccess) "Pipeline Complete!" else "Pipeline Failed!", 
                style = MaterialTheme.typography.titleLarge, 
                color = if (isSuccess) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        } else {
            Button(onClick = {
                // TODO: Step 1 - Create WorkRequests for each worker
                // 1. Cleanup Work (no input)
                // 2. Blur Work (no input)
                // 3. Grayscale Work (takes output from Blur)
                // 4. Watermark Work (takes output from Grayscale)


                // TODO: Step 2 - Chain the workers
                // Use WorkManager to run them in order: Cleanup -> Blur -> Grayscale -> Watermark
                // Use .beginWith().then()...
                
                // val chain = ...

                // TODO: Step 3 - Observe the FINAL worker
                // Save its ID to the 'workId' state variable to update UI
                
                // workId = ...
                
                showProgress = true
            }) {
                Text("Start Filter Pipeline")
            }
        }
    }
}
