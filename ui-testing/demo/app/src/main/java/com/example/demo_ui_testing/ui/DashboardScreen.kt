package com.example.demo_ui_testing.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo_ui_testing.R

/**
 * DashboardScreen displays the welcome page after successful authentication.
 * Used for verifying navigation success in UI tests.
 */
@Composable
fun DashboardScreen(username: String) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.dashboard_welcome),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.`text`.style.TextAlign.Center
            )

            Text(
                text = "Logged in as: $username",
                fontSize = 18.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .testTag("welcome_message")
            )
        }
    }
}
