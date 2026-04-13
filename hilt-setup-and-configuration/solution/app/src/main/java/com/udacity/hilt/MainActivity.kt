package com.udacity.hilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.udacity.hilt.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HiltDemoApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun HiltDemoApp(viewModel: MainViewModel) {
    val userState = viewModel.user.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hilt Demo (Solution)", style = MaterialTheme.typography.headlineLarge)
        
        Sp acer(modifier = Modifier.height(32.dp))

        if (isLoading.value) {
            CircularProgressIndicator()
        } else {
            userState.value?.let { user ->
                Card(modifier = Modifier.padding(16.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Name: ${user.name}", style = MaterialTheme.typography.titleMedium)
                        Text("Email: ${user.email}", style = MaterialTheme.typography.bodyMedium)
                        Text("Role: ${user.role}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
