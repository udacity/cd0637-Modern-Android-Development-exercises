package com.example.demo_compose_layout_system

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * MainActivity demonstrating the Jetpack Compose Layout System.
 * Covers Rows, Columns, Box, Modifiers, Weight, and Nested Layouts.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutSystemTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DashboardScreen()
                }
            }
        }
    }
}

@Composable
fun LayoutSystemTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = Color(0xFF6200EE),
            secondary = Color(0xFF03DAC6)
        ),
        content = content
    )
}

@Composable
fun DashboardScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // 1. Toolbar using Row and Arrangement
        CustomToolbar()

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 2. Status Card using Box for stacking
            StatusBanner()

            // 3. Quick Actions using Nested Rows and Columns
            QuickActionGrid()

            Text(
                text = stringResource(R.string.tasks_header),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            // 4. Task List using Column and Weight
            TaskList(modifier = Modifier.weight(1f))

            // 5. Vertical Form Layout
            NewTaskForm()
        }
    }
}

@Composable
fun CustomToolbar() {
    // Row layout for horizontal arrangement
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // Push items to ends
    ) {
        IconButton(onClick = { }) {
            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
        }
        
        Text(
            text = stringResource(R.string.dashboard_title),
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        
        IconButton(onClick = { }) {
            Icon(Icons.Default.AccountCircle, contentDescription = "Profile", tint = Color.White)
        }
    }
}

@Composable
fun StatusBanner() {
    // Box layout for stacking elements on top of each other
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFFBBDEFB), Color(0xFF90CAF9))
                )
            )
            .padding(16.dp)
    ) {
        // Background Stacked Icon
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterEnd) // Position in Box
                .padding(end = 8.dp),
            tint = Color.White.copy(alpha = 0.3f)
        )
        
        Column(modifier = Modifier.align(Alignment.CenterStart)) {
            Text(
                text = stringResource(R.string.welcome_message),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.status_label),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun QuickActionGrid() {
    Column {
        Text(
            text = stringResource(R.string.quick_actions_header),
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Combining Rows to form a grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            QuickActionButton(
                icon = Icons.Default.Settings,
                label = stringResource(R.string.action_settings),
                modifier = Modifier.weight(1f) // Equal width distribution
            )
            QuickActionButton(
                icon = Icons.Default.CheckCircle,
                label = stringResource(R.string.action_tasks),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            QuickActionButton(
                icon = Icons.Default.Star,
                label = stringResource(R.string.action_stats),
                modifier = Modifier.weight(1f)
            )
            QuickActionButton(
                icon = Icons.Default.AccountCircle,
                label = stringResource(R.string.action_profile),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun QuickActionButton(icon: ImageVector, label: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(60.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = label, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun TaskList(modifier: Modifier = Modifier) {
    // Column with weight will fill available space
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TaskItem(stringResource(R.string.task_1))
        TaskItem(stringResource(R.string.task_2))
        TaskItem(stringResource(R.string.task_3))
    }
}

@Composable
fun TaskItem(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFECEFF1))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun NewTaskForm() {
    // Vertical Form Layout using Column
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = stringResource(R.string.new_task_hint),
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.add_task_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    LayoutSystemTheme {
        DashboardScreen()
    }
}
