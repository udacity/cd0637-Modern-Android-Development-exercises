package com.example.demo_advanced_background_work.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MediaHubScreen(viewModel: MediaHubViewModel) {
    val logs by viewModel.logs.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Media Hub: Background Pipeline",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { viewModel.startProcessing() }) {
                Text("Start Pipeline")
            }
            Button(
                onClick = { viewModel.cancelWork() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Cancel All")
            }
        }

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Processing Logs:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start)
        )

        Card(
            modifier = Modifier.fillMaxSize().padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(logs) { log ->
                    Text(
                        text = log,
                        color = Color.Green,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
