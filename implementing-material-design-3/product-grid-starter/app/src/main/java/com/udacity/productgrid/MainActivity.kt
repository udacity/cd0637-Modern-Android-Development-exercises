package com.udacity.productgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
// import com.udacity.productgrid.ui.theme.AppTheme  // TODO: Uncomment after implementing Theme.kt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // TODO: Step 1 - After implementing Theme.kt, replace MaterialTheme with AppTheme
            // Also add dark theme state management:
            // var isDarkTheme by remember { mutableStateOf(false) }
            // AppTheme(darkTheme = isDarkTheme) { ... }
            // Pass isDarkTheme and setter to ProductGridScreen
            
            // For now, using MaterialTheme as placeholder
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductGridScreen()
                }
            }
        }
    }
}

// TODO: Step 2 - Create a Product data class
// Fields: id: Int, name: String, price: String, emoji: String

/**
 * ProductGridScreen - Main screen showing product catalog
 * 
 * TODO: Implement product grid with Material3 theming
 */
@Composable
fun ProductGridScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // TODO: Step 3 - Add theme toggle
        // Create state: var isDarkTheme by remember { mutableStateOf(false) }
        // Add Row with "Dark Mode" text and Switch
        
        // TODO: Step 4 - Add title
        // Use Text with MaterialTheme.typography.headlineMedium
        
        // TODO: Step 5 - Create sample products list
        // At least 6-8 products with emoji, name, and price
        
        // TODO: Step 6 - Implement LazyVerticalGrid
        // Use GridCells.Fixed(2) for 2 columns
        // Use items() to display ProductCard for each product
        // Add contentPadding and spacing
    }
}

/**
 * ProductCard - Displays a single product
 * 
 * TODO: Implement this composable
 * 
 * @param product The product to display
 * 
 * Should use:
 * - Card composable from Material3
 * - Column for vertical layout
 * - Text for emoji (large size), name, and price
 * - MaterialTheme colors and typography
 */
@Composable
fun ProductCard(
    // TODO: Add product parameter
) {
    // TODO: Implement product card
    // Use Card with appropriate modifier
    // Display emoji, product name, and price
    // Apply MaterialTheme styling
}

@Preview(showBackground = true)
@Composable
fun ProductGridPreview() {
    MaterialTheme {
        ProductGridScreen()
    }
}
