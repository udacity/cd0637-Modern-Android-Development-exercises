package com.example.demo_integrating_compose_with_viewmodel.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotivationScreen(
    modifier: Modifier = Modifier,
    viewModel: MotivationViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val moodState by viewModel.moodState.collectAsState()

    // Smoothly animate background color based on mood
    val backgroundColor by animateColorAsState(
        targetValue = when (moodState) {
            AppMood.CALM -> Color(0xFFE3F2FD) // Light Blue
            AppMood.ENERGETIC -> Color(0xFFFFF3E0) // Light Orange
            AppMood.FOCUSED -> Color(0xFFF1F8E9) // Light Green
        },
        animationSpec = tween(durationMillis = 500)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Daily Motivation",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // UI Content based on state
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is MotivationUiState.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
                is MotivationUiState.Success -> {
                    val quoteData = (uiState as MotivationUiState.Success).quote
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "\"${quoteData.quote}\"",
                            fontSize = 24.sp,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
                            lineHeight = 32.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "- ${quoteData.author}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                is MotivationUiState.Error -> {
                    Text(
                        text = (uiState as MotivationUiState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Mood Selection Buttons
        Text("Change Mood:", style = MaterialTheme.typography.labelLarge)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MoodButton("Calm", AppMood.CALM, moodState == AppMood.CALM) { viewModel.updateMood(it) }
            MoodButton("Energetic", AppMood.ENERGETIC, moodState == AppMood.ENERGETIC) { viewModel.updateMood(it) }
            MoodButton("Focused", AppMood.FOCUSED, moodState == AppMood.FOCUSED) { viewModel.updateMood(it) }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.fetchNewQuote() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Next Quote")
        }
    }
}

@Composable
fun MoodButton(
    text: String,
    mood: AppMood,
    isSelected: Boolean,
    onClick: (AppMood) -> Unit
) {
    FilterChip(
        selected = isSelected,
        onClick = { onClick(mood) },
        label = { Text(text) }
    )
}
