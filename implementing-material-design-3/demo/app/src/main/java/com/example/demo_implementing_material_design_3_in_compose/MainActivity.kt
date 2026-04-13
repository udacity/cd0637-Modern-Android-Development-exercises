package com.example.demo_implementing_material_design_3_in_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.example.demo_implementing_material_design_3_in_compose.ui.theme.Demo02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Demo02Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    M3DemoScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun M3DemoScreen() {
    var taskText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text("Material 3 Lab") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Action */ },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // FORM SECTION: TextField & Filled Button
            item {
                Text(
                    text = "Build a Form",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = taskText,
                    onValueChange = { taskText = it },
                    label = { Text("Task Description") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* Submit */ },
                ) {
                    Text("Add Task")
                }
            }

            // CARD SECTION: Tonal Elevation & Custom Shapes
            item {
                Text(
                    text = "Card UI & Tonal Elevation",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            items(4) { index ->
                // ElevatedCard demonstrates tonal elevation (surface tinting)
                // First card has 0 elevation
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = (index * 4).dp)
                ) {
                    ListItem(
                        headlineContent = { Text("Task Priority $index") },
                        supportingContent = { Text("Higher elevation adds more Primary color tint.") },
                        leadingContent = {
                            Icon(
                                Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        trailingContent = {
                            // Tonal Button uses Secondary/Tertiary colors
                            FilledTonalButton(onClick = { }) {
                                Text("Details")
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    // This allows you to select a specific M3 wallpaper style in the preview
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
)
@Composable
fun DynamicColorPreview() {
    Demo02Theme(dynamicColor = true) {
        M3DemoScreen()
    }
}