package com.example.demo_building_your_first_composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * MainActivity is the entry point for the Greeting Card Demo.
 * It demonstrates how to build and nest composables to create a custom UI.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingCardScreen()
                }
            }
        }
    }
}

@Composable
fun GreetingAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = Color(0xFF00796B),
            secondary = Color(0xFF004D40)
        ),
        content = content
    )
}

@Composable
fun GreetingCardScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE0F2F1), Color(0xFFB2DFDB))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        GreetingCard(
            recipient = stringResource(R.string.recipient_name),
            message = stringResource(R.string.greeting_message),
            sender = stringResource(R.string.sender_name),
            onSendClick = { /* Action */ }
        )
    }
}

/**
 * A custom Greeting Card composable that demonstrates:
 * - Using Material3 Card
 * - Layout with Column
 * - Styling with Typography and Colors
 * - Responding to UI interactions
 */
@Composable
fun GreetingCard(
    recipient: String,
    message: String,
    sender: String,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = stringResource(R.string.greeting_icon_description),
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Dear $recipient,",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                color = Color.DarkGray
            )

            Text(
                text = sender,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.End),
                color = MaterialTheme.colorScheme.secondary
            )

            Button(
                onClick = onSendClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.send_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingCardPreview() {
    GreetingAppTheme {
        GreetingCardScreen()
    }
}
