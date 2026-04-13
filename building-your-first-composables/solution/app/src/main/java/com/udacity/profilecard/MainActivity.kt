package com.udacity.profilecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * MainActivity is the entry point of the application.
 * It sets up the Compose UI using setContent and displays the ProfileCard.
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
                    // Display the ProfileCard with sample data
                    ProfileCard(
                        name = stringResource(R.string.sample_name),
                        bio = stringResource(R.string.sample_bio),
                        onContactClick = { /* Handle contact click */ }
                    )
                }
            }
        }
    }
}

/**
 * ProfileCard is a reusable composable that displays user profile information.
 * 
 * This composable demonstrates:
 * - Creating a custom UI component with Jetpack Compose
 * - Using Column layout for vertical arrangement
 * - Combining multiple composables (Icon, Text, Button)
 * - Accepting parameters to make the component reusable
 * - Using Material Design 3 components
 * 
 * @param name The user's name to display
 * @param bio A short biography or description of the user
 * @param onContactClick Callback function invoked when the contact button is clicked
 */
@Composable
fun ProfileCard(
    name: String,
    bio: String,
    onContactClick: () -> Unit
) {
    // Column arranges child composables vertically
    Column(
        modifier = Modifier.padding(16.dp), // Add padding around the entire card
        horizontalAlignment = Alignment.CenterHorizontally, // Center items horizontally
        verticalArrangement = Arrangement.Center // Center items vertically
    ) {
        // Icon composable displays a vector icon from Material Icons
        Icon(
            imageVector = Icons.Default.Person, // Use the Person icon as a profile picture
            contentDescription = stringResource(R.string.profile_picture_description), // Accessibility description from resources
            modifier = Modifier.size(100.dp), // Set the icon size to 100dp
            tint = Color(0xFF6200EE) // Apply a purple tint color
        )
        
        // Text composable displays the user's name
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium, // Use headline typography
            fontWeight = FontWeight.Bold, // Make the text bold
            modifier = Modifier.padding(top = 16.dp) // Add spacing above the name
        )
        
        // Text composable displays the user's bio
        Text(
            text = bio,
            style = MaterialTheme.typography.bodyLarge, // Use body text typography
            textAlign = TextAlign.Center, // Center the text horizontally
            modifier = Modifier.padding(top = 8.dp, start = 24.dp, end = 24.dp) // Add spacing and horizontal padding
        )
        
        // Button composable creates a clickable button
        Button(
            onClick = onContactClick, // Invoke the callback when clicked
            modifier = Modifier.padding(top = 16.dp) // Add spacing above the button
        ) {
            // The text inside the button from string resources
            Text(stringResource(R.string.contact_button))
        }
    }
}

/**
 * Preview function allows you to see the ProfileCard in Android Studio's preview panel.
 * This speeds up development by showing UI changes without running the app.
 * 
 * The @Preview annotation tells Android Studio to render this composable.
 * You can customize the preview with parameters like:
 * - showBackground = true (shows a white background)
 * - uiMode = UI_MODE_NIGHT_YES (shows dark theme)
 */
@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    MaterialTheme {
        ProfileCard(
            name = stringResource(R.string.sample_name),
            bio = stringResource(R.string.sample_bio),
            onContactClick = { }
        )
    }
}
