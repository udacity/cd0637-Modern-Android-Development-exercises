package com.udacity.moodtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
 * MoodTrackerScreen - Main composable for the mood tracker
 * 
 * TODO: Implement state management and UI for mood tracking
 * This should be a STATEFUL composable that manages the selected mood
 */
@Composable
fun MoodTrackerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // TODO: Step 1 - Create state for selected mood
        // Use: var selectedMood by remember { mutableStateOf("Happy") }
        
        // TODO: Step 2 - Display the prompt text
        // Use stringResource(R.string.select_mood_prompt)
        
        // TODO: Step 3 - Display the selected mood message
        // Show something like "You're feeling Happy!"
        // Use the selectedMood state value
        
        // TODO: Step 4 - Create a Column with mood buttons
        // The Column should contain multiple MoodButton composables (stacked vertically)
        // Use Column with Arrangement.spacedBy(12.dp) for spacing
        // Moods to include: Happy (😊), Neutral (😐), Sad (😢), Angry (😡), Excited (🎉)
        // Each button should:
        // - Display the emoji and label horizontally (side by side)
        // - Show if it's selected (different styling)
        // - Update selectedMood when clicked
        // Hint: Use fillMaxWidth(0.7f) to make buttons wide enough to show full text
        
        // TODO: Step 5 - Implement the MoodButton composable below
        // It should be STATELESS and receive all data through parameters
    }
}

/**
 * MoodButton - A stateless composable for displaying a mood option
 * 
 * TODO: Implement this stateless composable
 * 
 * @param emoji The emoji to display
 * @param label The mood label
 * @param isSelected Whether this mood is currently selected
 * @param onClick Callback when the button is clicked
 * 
 * Hint: Use a Row inside the Button to show emoji and label side by side
 * Make the button wide enough using fillMaxWidth(0.7f)
 */
@Composable
fun MoodButton(
    emoji: String,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // TODO: Implement the mood button
    // Hint: Use Button composable with conditional styling based on isSelected
    // Show emoji and label, change background color when selected
}

@Preview(showBackground = true)
@Composable
fun MoodTrackerPreview() {
    MaterialTheme {
        MoodTrackerScreen()
    }
}
