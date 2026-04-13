package com.udacity.uitesting.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen(username: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Welcome, $username!",
            fontSize = 24.sp,
            modifier = Modifier.testTag("welcome_message")
        )
    }
}
