package com.example.mocking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mocking.data.FakeUserDatabase
import com.example.mocking.data.User
import com.example.mocking.data.UserApiService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Manual DI for simplicity in this demo
        val database = FakeUserDatabase()
        val api = object : UserApiService {
            override fun getUsers(): List<User> {
                return listOf(User("1", "Demo User"))
            }
        }
        val syncManager = UserSyncManager(api, database)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DemoScreen(syncManager)
                }
            }
        }
    }
}

@Composable
fun DemoScreen(syncManager: UserSyncManager) {
    var syncResult by remember { mutableStateOf<Boolean?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Mocking and Fakes Demo", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            syncResult = syncManager.syncUsers()
        }) {
            Text("Sync Users")
        }
        Spacer(modifier = Modifier.height(16.dp))
        syncResult?.let {
            Text(
                text = if (it) "Sync Successful!" else "Sync Failed!",
                color = if (it) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
    }
}
