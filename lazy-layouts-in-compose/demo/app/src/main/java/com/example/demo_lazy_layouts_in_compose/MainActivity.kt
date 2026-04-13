package com.example.demo_lazy_layouts_in_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.demo_lazy_layouts_in_compose.ui.MediaGalleryScreen
import com.example.demo_lazy_layouts_in_compose.ui.theme.Demo02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Demo02Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MediaGalleryScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}