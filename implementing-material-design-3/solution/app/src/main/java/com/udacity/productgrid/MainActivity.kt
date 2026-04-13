package com.udacity.productgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udacity.productgrid.ui.theme.AppTheme

/**
 * Product data class representing a product in the catalog.
 * 
 * In a real app, this would likely include more fields like description,
 * image URL, category, etc. For this exercise, we use emoji as a visual placeholder.
 */
data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val emoji: String
)

/**
 * MainActivity is the entry point of the application.
 * It sets up the Compose UI with our custom AppTheme.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Manage dark theme state at the activity level
            var isDarkTheme by remember { mutableStateOf(false) }
            
            // Wrap the app with our custom theme
            // The theme will provide colors, typography, and shapes throughout the app
            AppTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductGridScreen(
                        isDarkTheme = isDarkTheme,
                        onThemeChange = { isDarkTheme = it }
                    )
                }
            }
        }
    }
}

/**
 * ProductGridScreen - Main screen displaying the product catalog.
 * 
 * This composable demonstrates:
 * - State management for theme toggling
 * - LazyVerticalGrid for efficient grid layouts
 * - Material3 Switch component
 * - Typography hierarchy
 * 
 * @param isDarkTheme Current theme state
 * @param onThemeChange Callback to change the theme
 */
@Composable
fun ProductGridScreen(
    isDarkTheme: Boolean = false,
    onThemeChange: (Boolean) -> Unit = {}
) {
    // Sample products for the catalog
    // In a real app, this would come from a repository/ViewModel
    val products = remember {
        listOf(
            Product(1, "Laptop", "$999", "💻"),
            Product(2, "Smartphone", "$699", "📱"),
            Product(3, "Headphones", "$199", "🎧"),
            Product(4, "Camera", "$899", "📷"),
            Product(5, "Watch", "$399", "⌚"),
            Product(6, "Tablet", "$599", "📟"),
            Product(7, "Keyboard", "$149", "⌨️"),
            Product(8, "Mouse", "$79", "🖱️")
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Theme toggle row at the top
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.dark_mode_label),
                style = MaterialTheme.typography.bodyLarge
            )
            // Material3 Switch for theme toggling
            Switch(
                checked = isDarkTheme,
                onCheckedChange = onThemeChange
            )
        }
        
        // Catalog title
        Text(
            text = stringResource(R.string.product_catalog_title),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Product grid using LazyVerticalGrid
        // LazyVerticalGrid is a lazy composable that only composes visible items
        // GridCells.Fixed(2) creates a grid with 2 columns
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { product ->
                ProductCard(product = product)
            }
        }
    }
}

/**
 * ProductCard - A card displaying a single product.
 * 
 * This composable demonstrates:
 * - Material3 Card component with automatic elevation
 * - Using MaterialTheme colors and typography
 * - Proper semantic color usage (surface, onSurface)
 * - Layout composition with Column
 * 
 * @param product The product to display
 */
@Composable
fun ProductCard(product: Product) {
    // Material3 Card automatically handles elevation with tonal surfaces
    // No need to specify elevation manually - it adapts based on the theme
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Product emoji as visual
            Text(
                text = product.emoji,
                fontSize = 48.sp
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Product name using Material3 typography
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            // Product price with secondary color
            Text(
                text = product.price,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

/**
 * Preview function for the product grid.
 * Shows both light and dark theme variants in Android Studio.
 */
@Preview(showBackground = true, name = "Light Theme")
@Preview(showBackground = true, name = "Dark Theme", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProductGridPreview() {
    AppTheme {
        ProductGridScreen()
    }
}
