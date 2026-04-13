package com.example.demo_advanced_compose_patterns.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_advanced_compose_patterns.ui.components.CustomVerticalLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedComposeScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(true) }
    var data by remember { mutableStateOf<List<String>>(emptyList()) }

    // 1. LaunchedEffect: Simulate an API call on launch
    LaunchedEffect(Unit) {
        delay(2000) // Simulate network delay
        data = listOf("Item 1", "Item 2", "Item 3", "Item 4")
        isLoading = false
    }

    // 2. DisposableEffect: Resource cleanup demonstration
    DisposableEffect(Unit) {
        Log.d("AdvancedCompose", "Resource Acquired: Initializing listener...")
        onDispose {
            Log.d("AdvancedCompose", "Resource Released: Cleaning up listener...")
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Advanced Compose Patterns") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            // 3. rememberCoroutineScope: Trigger Snackbar from FAB
            FloatingActionButton(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("FAB Clicked! Snackbar triggered via CoroutineScope.")
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                // 4. Custom Layout Measurement
                CustomVerticalLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    spacing = 12.dp
                ) {
                    data.forEach { item ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Text(
                                text = item,
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                    
                    // Add a special footer showing how measurement works
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(MaterialTheme.colorScheme.secondaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Custom Footer (Fixed Height)",
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdvancedComposeScreenPreview() {
    MaterialTheme {
        AdvancedComposeScreen()
    }
}
