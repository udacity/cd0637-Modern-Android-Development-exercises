package com.example.demo_hilt_setup_and_configuration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.example.demo_hilt_setup_and_configuration.ui.MainViewModel
import com.example.demo_hilt_setup_and_configuration.ui.theme.Demo02Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val viewModel: MainViewModel by viewModels()

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
                        ProfileCard(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileCard(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val user = viewModel.user

    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = user.name.take(1), 
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer, 
                        fontWeight = Bold
                    )
                }

                Spacer(Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) { 
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.titleLarge, 
                        fontWeight = Bold
                    )
                    Text(
                        text = user.role,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = user.bio,
                style = MaterialTheme.typography.bodyLarge,
            )
            
            Spacer(Modifier.height(16.dp))
            
            Text(
                text = "This data was injected using Hilt!",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}