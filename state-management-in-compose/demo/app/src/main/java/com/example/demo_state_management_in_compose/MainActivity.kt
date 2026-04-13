package com.example.demo_state_management_in_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_state_management_in_compose.ui.theme.Demo02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Demo02Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FocusDashboardScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun FocusDashboardScreen(modifier: Modifier = Modifier) {
    var isFocusModeEnabled by remember { mutableStateOf(false) }
    var sessionsCompleted by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Attention Span Repair",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(32.dp))

        FocusSwitch(
            isFocused = isFocusModeEnabled,
            onToggleChange = { isFocusModeEnabled = it }
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (isFocusModeEnabled) {
            SessionTracker(
                count = sessionsCompleted,
                onSessionComplete = {
                    sessionsCompleted++
                }
            )
        }
    }

}

@Composable
fun SessionTracker(count: Int, onSessionComplete: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Completed Sessions: $count",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onSessionComplete) {
            Text("Log Deep Work Session")
        }
    }
}

@Composable
fun FocusSwitch(isFocused: Boolean, onToggleChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Deep Work Mode: ${if (isFocused) "ACTIVE" else "OFF"}")
        Spacer(modifier = Modifier.width(16.dp))
        Switch(
            checked = isFocused,
            onCheckedChange = onToggleChange
        )
    }
}


@Preview(showBackground = true)
@Composable
fun FocusDashboardPreview() {
    Demo02Theme {
        FocusDashboardScreen(
            modifier = Modifier.padding(16.dp)
        )
    }
}