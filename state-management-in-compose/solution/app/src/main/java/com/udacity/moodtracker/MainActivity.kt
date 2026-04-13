package com.udacity.moodtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * MainActivity is the entry point of the application.
 * It sets up the Compose UI and displays the MoodTrackerScreen.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MoodTrackerScreen()
                }
            }
        }
    }
}

/**
 * MoodTrackerScreen - A stateful composable that manages mood selection state.
 * 
 * This composable demonstrates:
 * - State management with remember and mutableStateOf
 * - State hoisting pattern (this parent owns the state)
 * - Passing state and callbacks to stateless children
 * 
 * The state is hoisted here because this is the lowest common ancestor
 * that needs to coordinate between the message display and the buttons.
 */
@Composable
fun MoodTrackerScreen() {
    // Create state for the selected mood
    // 'by' keyword enables property delegation for cleaner syntax
    // remember ensures the state survives recomposition
    // mutableStateOf makes the state observable (triggers recomposition on change)
    var selectedMood by remember { mutableStateOf("Happy") }
    
    // Define mood options - could be a data class for more complex scenarios
    val moods = listOf(
        "Happy" to "😊",
        "Neutral" to "😐",
        "Sad" to "😢",
        "Angry" to "😡",
        "Excited" to "🎉"
    )
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Prompt text
        Text(
            text = stringResource(R.string.select_mood_prompt),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Display the selected mood message
        // This demonstrates reactive UI - it automatically updates when selectedMood changes
        Text(
            text = "${stringResource(R.string.mood_message_prefix)} $selectedMood!",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Column of mood buttons (vertical layout to show full text)
        // We hoist the state up, so these buttons are stateless
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            moods.forEach { (label, emoji) ->
                MoodButton(
                    emoji = emoji,
                    label = label,
                    isSelected = selectedMood == label, // Derived from state
                    onClick = { selectedMood = label } // Update state on click
                )
            }
        }
    }
}

/**
 * MoodButton - A completely stateless composable for displaying a mood option.
 * 
 * This composable demonstrates:
 * - Stateless composable design (no state inside)
 * - Receiving all data through parameters
 * - Reporting events up through callbacks
 * - Conditional UI based on parameters
 * 
 * Being stateless makes this composable:
 * - Reusable in different contexts
 * - Easier to test (no hidden state)
 * - Predictable (same inputs = same output)
 * 
 * @param emoji The emoji icon to display
 * @param label The mood label text
 * @param isSelected Whether this mood is currently selected
 * @param onClick Callback invoked when the button is clicked
 */
@Composable
fun MoodButton(
    emoji: String,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // Button with conditional styling based on isSelected
    // Wider horizontal layout to show full text
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.7f) // 70% of screen width
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            // Change background color when selected
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        // Horizontal layout: emoji on left, label on right
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Emoji icon
            Text(
                text = emoji,
                fontSize = 28.sp
            )
            Spacer(modifier = Modifier.width(12.dp))
            // Label text
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) {
                    Color.White
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }
}

/**
 * Preview function for rapid development.
 * Shows the MoodTrackerScreen in the preview panel.
 */
@Preview(showBackground = true)
@Composable
fun MoodTrackerPreview() {
    MaterialTheme {
        MoodTrackerScreen()
    }
}
