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

private const val TAG = "TimerScreen"
private const val INITIAL_TIME = 60 // 60 seconds = 1 minute

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
 * TimerScreen - Countdown timer demonstrating side effects.
 * 
 * This composable demonstrates:
 * - LaunchedEffect for running coroutines tied to composition
 * - DisposableEffect for cleanup
 * - State management with timers
 * - Proper side effect patterns
 */
@Composable
fun TimerScreen() {
    // State for time remaining (in seconds)
    var timeRemaining by remember { mutableStateOf(INITIAL_TIME) }
    
    // State for whether the timer is running
    var isRunning by remember { mutableStateOf(false) }
    
    // LaunchedEffect: Run timer coroutine
    // Key: isRunning - effect restarts when this changes
    // When isRunning changes from true to false, the previous coroutine is cancelled
    LaunchedEffect(isRunning) {
        Log.d(TAG, "LaunchedEffect started, isRunning=$isRunning")
        
        // Only run the timer if isRunning is true
        while (isRunning && timeRemaining > 0) {
            // Wait for 1 second
            delay(1000)
            
            // Decrement time
            timeRemaining--
            
            Log.d(TAG, "Timer tick: $timeRemaining seconds remaining")
            
            // Stop automatically when reaching 0
            if (timeRemaining == 0) {
                isRunning = false
                Log.d(TAG, "Timer completed!")
            }
        }
        
        Log.d(TAG, "LaunchedEffect completed")
    }
    
    // DisposableEffect: Cleanup when composable leaves composition
    // Key: Unit - runs once on composition
    DisposableEffect(Unit) {
        Log.d(TAG, "TimerScreen entered composition")
        
        // onDispose is called when the composable leaves composition
        onDispose {
            Log.d(TAG, "TimerScreen leaving composition - cleanup!")
            // This is where you would clean up resources like:
            // - Cancelling API calls
            // - Unregistering listeners
            // - Releasing resources
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = stringResource(R.string.timer_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Timer display in MM:SS format
        val minutes = timeRemaining / 60
        val seconds = timeRemaining % 60
        val formattedTime = String.format("%02d:%02d", minutes, seconds)
        
        Text(
            text = formattedTime,
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        // Control buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Start/Pause button
            Button(
                onClick = {
                    // Toggle running state
                    // This will trigger LaunchedEffect to restart
                    isRunning = !isRunning
                    Log.d(TAG, "Button clicked: isRunning toggled to $isRunning")
                },
                enabled = timeRemaining > 0 // Disable if time is 0
            ) {
                Text(
                    text = if (isRunning) {
                        stringResource(R.string.pause_button)
                    } else {
                        stringResource(R.string.start_button)
                    }
                )
            }
            
            // Reset button
            Button(
                onClick = {
                    // Reset to initial time and stop
                    timeRemaining = INITIAL_TIME
                    isRunning = false
                    Log.d(TAG, "Timer reset to $INITIAL_TIME seconds")
                }
            ) {
                Text(stringResource(R.string.reset_button))
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Instruction text
        Text(
            text = "Check Logcat for side effect logs",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    MaterialTheme {
        TimerScreen()
    }
}
