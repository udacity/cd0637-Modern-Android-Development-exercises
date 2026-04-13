package com.example.demo_advanced_compose_patterns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.demo_advanced_compose_patterns.ui.screens.AdvancedComposeScreen
import com.example.demo_advanced_compose_patterns.ui.theme.Demo02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Demo02Theme {
                AdvancedComposeScreen()
            }
        }
    }
}