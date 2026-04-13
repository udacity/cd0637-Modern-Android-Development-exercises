package com.example.demo_implementing_workmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.work.*
import com.example.demo_implementing_workmanager.ui.theme.Demo02Theme
import kotlinx.coroutines.flow.emptyFlow
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Demo02Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        SyncManager(modifier = Modifier.fillMaxSize())
                    }
                }
            }
        }
    }
}

@Composable
fun SyncManager(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val workManager = remember { WorkManager.getInstance(context) }
    
    // Track the ID of the enqueued work
    var workId by remember { mutableStateOf<UUID?>(null) }
    
    // Modern way: Observe work status as a Flow
    val workInfo by remember(workId) {
        if (workId != null) {
            workManager.getWorkInfoByIdFlow(workId!!)
        } else {
            emptyFlow<WorkInfo>()
        }
    }.collectAsStateWithLifecycle(initialValue = null)

    val state = workInfo?.state
    val isRunning = state == WorkInfo.State.RUNNING || state == WorkInfo.State.ENQUEUED
    val isFinished = state?.isFinished ?: false

    Column(
        modifier = modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Background Sync Demo",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = Bold
        )
        
        Spacer(Modifier.height(8.dp))
        
        Text(
            text = "This demo shows how WorkManager executes tasks even if the app is closed.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StatusLogo(state)

                Spacer(Modifier.height(16.dp))

                Text(
                    text = when {
                        state == null -> "Ready to Sync"
                        state == WorkInfo.State.ENQUEUED -> "Waiting for Internet..."
                        state == WorkInfo.State.RUNNING -> "Syncing Data..."
                        state == WorkInfo.State.SUCCEEDED -> "Sync Successful!"
                        state == WorkInfo.State.FAILED -> "Sync Failed"
                        else -> "Status: $state"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = Bold,
                    color = if (state == WorkInfo.State.SUCCEEDED) 
                        MaterialTheme.colorScheme.primary 
                    else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        if (isRunning) {
            CircularProgressIndicator(modifier = Modifier.size(48.dp))
            Spacer(Modifier.height(16.dp))
            Text("WorkManager is working in background...")
        } else {
            Button(
                onClick = {
                    // 1. Define Constraints (Requires Internet)
                    val constraints = Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()

                    // 2. Create the Request
                    val syncRequest = OneTimeWorkRequestBuilder<SyncWorker>()
                        .setConstraints(constraints)
                        .build()

                    // 3. Enqueue the work
                    workManager.enqueue(syncRequest)
                    
                    // 4. Track ID to observe
                    workId = syncRequest.id
                },
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text(if (isFinished) "Sync Again" else "Start Background Sync")
            }
        }
    }
}

@Composable
fun StatusLogo(state: WorkInfo.State?) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(
                when (state) {
                    WorkInfo.State.SUCCEEDED -> MaterialTheme.colorScheme.primary
                    WorkInfo.State.RUNNING -> MaterialTheme.colorScheme.secondary
                    else -> MaterialTheme.colorScheme.outlineVariant
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = when (state) {
                WorkInfo.State.SUCCEEDED -> "✓"
                WorkInfo.State.RUNNING -> "⋯"
                else -> "!"
            },
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = Bold
        )
    }
}