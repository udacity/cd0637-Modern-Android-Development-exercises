package com.udacity.profilecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
 * ProfileCard Composable
 * 
 * TODO: Create a profile card that displays user information
 * 
 * @param name The user's name to display
 * @param bio A short biography or description
 * @param onContactClick Callback function when the contact button is clicked
 */
@Composable
fun ProfileCard(
    name: String,
    bio: String,
    onContactClick: () -> Unit
) {
    // Basic Column layout provided - you'll add the UI elements
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // TODO: Step 1 - Add a profile Icon
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = stringResource(R.string.profile_picture_description),
            modifier = Modifier.size(100.dp),
            tint = Color(0xFF6200EE)
        )
        // Use Icon composable with:
        // - imageVector = Icons.Default.Person
        // - contentDescription = stringResource(R.string.profile_picture_description)
        // - modifier = Modifier.size(100.dp) to make it large
        // - tint = Color(0xFF6200EE) for a purple color
        Text(
            text = "James Bond",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        
        // TODO: Step 2 - Add the name Text
        // Use Text composable to display the 'name' parameter with:
        // - style = MaterialTheme.typography.headlineMedium
        // - fontWeight = FontWeight.Bold
        // - modifier = Modifier.padding(top = 16.dp)
        Text(
            text = bio,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(
            modifier = Modifier.size(10.dp)
        )
        // TODO: Step 3 - Add the bio Text
        // Use Text composable to display the 'bio' parameter with:
        // - style = MaterialTheme.typography.bodyLarge
        // - textAlign = TextAlign.Center
        // - modifier = Modifier.padding(top = 8.dp, start = 24.dp, end = 24.dp)
        
        // TODO: Step 4 - Add spacing to the Button
        // The button is provided below, add proper spacing:
        // - Add modifier = Modifier.padding(top = 16.dp)
        Button(onClick = onContactClick) {
            Text(stringResource(R.string.contact_button))
        }
    }
}

/**
 * Preview function to see the ProfileCard in Android Studio
 * Tip: You can customize the preview with parameters like:
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
