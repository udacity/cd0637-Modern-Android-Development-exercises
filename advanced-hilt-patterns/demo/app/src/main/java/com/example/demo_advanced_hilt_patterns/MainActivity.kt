package com.example.demo_advanced_hilt_patterns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.demo_advanced_hilt_patterns.ui.theme.Demo02Theme
import com.example.demo_advanced_hilt_patterns.util.ScreenNavigator
import com.example.demo_advanced_hilt_patterns.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var navigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Demo02Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val data by viewModel.data.collectAsState()
                    
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Advanced Hilt Patterns",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(Modifier.height(16.dp))
                        
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text("Data from Repository:", style = MaterialTheme.typography.titleSmall)
                                Text(data, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                        
                        Spacer(Modifier.height(16.dp))
                        
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text("Activity Scoped Component:", style = MaterialTheme.typography.titleSmall)
                                Text(navigator.navigate())
                                Text("Note: ID remains same on orientation change if Activity is NOT recreated, but @ActivityScoped ensures instance is tied to Activity life.")
                            }
                        }
                    }
                }
            }
        }
    }
}