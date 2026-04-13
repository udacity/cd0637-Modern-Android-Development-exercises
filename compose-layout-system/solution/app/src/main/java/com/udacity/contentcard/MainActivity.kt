package com.udacity.contentcard

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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * MainActivity is the entry point of the application.
 * It sets up the Compose UI and displays the ContentCard in a centered layout.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // MaterialTheme provides consistent theming across the app
            MaterialTheme {
                // Surface is a container that applies Material Design styling
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Center the card in the middle of the screen
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        ContentCard(
                            title = stringResource(R.string.sample_title),
                            content = stringResource(R.string.sample_content),
                            timestamp = stringResource(R.string.sample_timestamp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * ContentCard is a reusable composable that displays a notification-style card.
 * 
 * This composable demonstrates:
 * - Nested layouts (Column containing Row and Column)
 * - Row for horizontal arrangement (header)
 * - Column for vertical arrangement (content)
 * - Modifier chaining for styling
 * - Spacer for fixed spacing
 * - Proper alignment and arrangement
 * 
 * @param title The title text to display in the header
 * @param content The main content text to display
 * @param timestamp The timestamp text to display below the content
 */
@Composable
fun ContentCard(
    title: String,
    content: String,
    timestamp: String
) {
    // Card provides Material Design elevation and shape
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Column arranges header and content sections vertically
        Column {
            // Header section using Row for horizontal layout
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Make the Row span the full width
                    .background(Color(0xFF6200EE)) // Purple background color
                    .padding(16.dp), // Add padding inside the Row
                verticalAlignment = Alignment.CenterVertically // Center items vertically
            ) {
                // Notification icon
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = stringResource(R.string.notification_icon_description),
                    tint = Color.White // White icon on purple background
                )
                
                // Fixed horizontal space between icon and title
                Spacer(modifier = Modifier.width(12.dp))
                
                // Title text
                Text(
                    text = title,
                    color = Color.White, // White text on purple background
                    fontWeight = FontWeight.Bold // Make the title bold
                )
            }
            
            // Content section using Column for vertical layout
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // Add padding around the content
            ) {
                // Main content text
                Text(
                    text = content,
                    fontSize = 14.sp // Standard body text size
                )
                
                // Fixed vertical space between content and timestamp
                Spacer(modifier = Modifier.height(8.dp))
                
                // Timestamp text with secondary styling
                Text(
                    text = timestamp,
                    fontSize = 12.sp, // Smaller text for timestamp
                    color = Color.Gray // Gray color to indicate secondary information
                )
            }
        }
    }
}

/**
 * Preview function allows you to see the ContentCard in Android Studio's preview panel.
 * This speeds up development by showing UI changes without running the app.
 * 
 * The @Preview annotation tells Android Studio to render this composable.
 * You can customize the preview with parameters like:
 * - showBackground = true (shows a white background)
 * - uiMode = UI_MODE_NIGHT_YES (shows dark theme)
 */
@Preview(showBackground = true)
@Composable
fun ContentCardPreview() {
    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            ContentCard(
                title = stringResource(R.string.sample_title),
                content = stringResource(R.string.sample_content),
                timestamp = stringResource(R.string.sample_timestamp)
            )
        }
    }
}
