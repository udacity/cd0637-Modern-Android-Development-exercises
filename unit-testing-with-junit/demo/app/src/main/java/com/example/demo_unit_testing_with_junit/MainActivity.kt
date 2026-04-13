package com.example.demo_unit_testing_with_junit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.demo_unit_testing_with_junit.ui.theme.Demo02Theme

class MainActivity : ComponentActivity() {
    
    private val viewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Demo02Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ScoreTrackerScreen(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun ScoreTrackerScreen(viewModel: ScoreViewModel) {
    val score by viewModel.score.collectAsStateWithLifecycle()
    val level by viewModel.level.collectAsStateWithLifecycle()
    val isComboActive by viewModel.isComboActive.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "PRO SCORE TRACKER",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = Bold,
            letterSpacing = 2.sp
        )

        Spacer(Modifier.height(32.dp))

        // Level Badge
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "LEVEL $level",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Spacer(Modifier.height(16.dp))

        // Score Display
        Text(
            text = score.toString(),
            style = MaterialTheme.typography.displayLarge,
            fontWeight = Bold,
            fontSize = 120.sp,
            color = if (isComboActive) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
        )

        if (isComboActive) {
            Text(
                text = "COMBO ACTIVE (2X POINTS)",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = Bold
            )
        }

        Spacer(Modifier.height(48.dp))

        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { viewModel.addPoints(10) },
                modifier = Modifier.weight(1f).height(64.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("+10 PTS")
            }

            Button(
                onClick = { viewModel.addPoints(-5) },
                modifier = Modifier.weight(1f).height(64.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer, contentColor = MaterialTheme.colorScheme.onErrorContainer)
            ) {
                Text("-5 PTS")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Toggle Combo and Reset
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = { viewModel.switchCombo() },
                modifier = Modifier.weight(1f).height(56.dp),
                border = if (isComboActive) ButtonDefaults.outlinedButtonBorder.copy(width = 2.dp) else ButtonDefaults.outlinedButtonBorder
            ) {
                Text(if (isComboActive) "STOP COMBO" else "START COMBO")
            }

            OutlinedButton(
                onClick = { viewModel.reset() },
                modifier = Modifier.weight(1f).height(56.dp)
            ) {
                Text("RESET")
            }
        }
    }
}

