package com.udacity.timer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TimerScreen()
                }
            }
        }
    }
}

/**
 * TimerScreen - Main screen with countdown timer
 * 
 * TODO: Implement timer with side effects
 */
@Composable
fun TimerScreen() {
    // TODO: Step 1 - Create state variables
    // var timeRemaining by remember { mutableStateOf(60) } // Start at 60 seconds
    // var isRunning by remember { mutableStateOf(false) }
    
    // TODO: Step 2 - Implement LaunchedEffect for timer
    // Use LaunchedEffect(isRunning) to run the timer
    // Inside the effect:
    // - Check if isRunning is true
    // - Use a while loop to continuously count down
    // - delay(1000) for 1-second intervals
    // - Decrement timeRemaining
    // - Stop when timeRemaining reaches 0
    
    // TODO: Step 3 - Implement DisposableEffect
    // Use DisposableEffect(Unit) for cleanup
    // Log when the screen appears
    // Use onDispose { } to log cleanup
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.timer_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // TODO: Step 4 - Display timer in MM:SS format
        // Format: val minutes = timeRemaining / 60
        //         val seconds = timeRemaining % 60
        //         String.format("%02d:%02d", minutes, seconds)
        Text(
            text = "00:00", // Replace with formatted time
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        // TODO: Step 5 - Implement control buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Start/Pause button
            Button(
                onClick = { /* TODO: Toggle isRunning */ }
            ) {
                Text(
                    // TODO: Show "Pause" if running, "Start" if not
                    text = stringResource(R.string.start_button)
                )
            }
            
            // Reset button
            Button(
                onClick = { /* TODO: Reset timeRemaining to 60, set isRunning to false */ }
            ) {
                Text(stringResource(R.string.reset_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    MaterialTheme {
        TimerScreen()
    }
}
