package com.example.demo_lazy_layouts_in_compose.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demo_lazy_layouts_in_compose.data.MediaItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaGalleryScreen(
    modifier: Modifier = Modifier,
    viewModel: MediaGalleryViewModel = viewModel()
) {
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val mediaItems by viewModel.filteredMedia.collectAsState(initial = emptyList())
    val categories = viewModel.categories

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Media Gallery",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Category Filter Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { viewModel.selectCategory(category) },
                    label = { Text(category) }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Responsive Grid
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 120.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize().animateContentSize()
        ) {
            items(mediaItems, key = { it.id }) { item ->
                MediaCard(item)
            }
        }
    }
}

@Composable
fun MediaCard(item: MediaItem) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().aspectRatio(1f)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Placeholder for image (using a colored box with initials)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(getRandomColor(item.category))
            ) {
                Text(
                    text = item.title.take(1),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White.copy(alpha = 0.5f)
                )
            }

            // Title overlay
            Surface(
                color = Color.Black.copy(alpha = 0.6f),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = item.title,
                    color = Color.White,
                    fontSize = 12.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

fun getRandomColor(category: String): Color {
    return when (category) {
        "Nature" -> Color(0xFF4CAF50)
        "Architecture" -> Color(0xFF2196F3)
        "People" -> Color(0xFFFF9800)
        else -> Color.Gray
    }
}
