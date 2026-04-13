package com.example.demo_advanced_testing_techniques

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.demo_advanced_testing_techniques.data.User
import com.example.demo_advanced_testing_techniques.data.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    val users: StateFlow<List<User>> = repository.getUsers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addUser(id: String, name: String) {
        viewModelScope.launch {
            repository.addUser(User(id, name, "$name@example.com"))
        }
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val users by viewModel.users.collectAsStateWithLifecycle()
                    
                    Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                        Text("Advanced Testing Demo", style = MaterialTheme.typography.headlineMedium)
                        Spacer(Modifier.height(16.dp))
                        
                        Button(onClick = { viewModel.addUser("user_${users.size}", "New User ${users.size}") }) {
                            Text("Add User")
                        }
                        
                        LazyColumn {
                            items(users) { user ->
                                Text("${user.name} (${user.email})", modifier = Modifier.padding(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}