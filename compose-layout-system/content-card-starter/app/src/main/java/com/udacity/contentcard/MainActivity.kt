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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
 * ContentCard Composable
 * 
 * TODO: Build a card with header and content sections using nested layouts
 * 
 * @param title The title to display in the header
 * @param content The main content text
 * @param timestamp The timestamp text to display
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
        // Basic Column layout provided - you'll add the header and content sections
        Column {
            // TODO: Step 1 - Build the header section
            // Create a Row composable with:
            // - modifier = Modifier.fillMaxWidth().background(Color(0xFF6200EE)).padding(16.dp)
            // - verticalAlignment = Alignment.CenterVertically
            // Inside the Row, add:
            // - Icon with imageVector = Icons.Default.Notifications, 
            //   contentDescription = stringResource(R.string.notification_icon_description),
            //   tint = Color.White
            // - Spacer with Modifier.width(12.dp)
            // - Text displaying 'title' parameter with color = Color.White, fontWeight = FontWeight.Bold
            
            // TODO: Step 2 - Build the content section
            // Create a Column composable with:
            // - modifier = Modifier.fillMaxWidth().padding(16.dp)
            // Inside the Column, add:
            // - Text displaying the 'content' parameter with fontSize = 14.sp
            // - Spacer with Modifier.height(8.dp)
            // - Text displaying the 'timestamp' parameter with:
            //   fontSize = 12.sp, color = Color.Gray
        }
    }
}

/**
 * Preview function to see the ContentCard in Android Studio
 * Tip: You can customize the preview with parameters like:
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
