package com.udacity.bottomnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomNavigationApp()
                }
            }
        }
    }
}

// TODO: Step 1 - Define navigation routes
// Create route constants for: home, profile, settings, detail/{itemId}
// You can use a sealed class, object, or simple string constants

/**
 * BottomNavigationApp - Main app composable with navigation
 * 
 * TODO: Implement navigation setup
 * 1. Create NavController with rememberNavController()
 * 2. Set up Scaffold with bottom bar
 * 3. Implement NavHost with all 3 routes
 * 4. Create bottom navigation bar
 */
@Composable
fun BottomNavigationApp() {
    // TODO: Step 2 - Create NavController
    // val navController = rememberNavController()
    
    // TODO: Step 3 - Implement Scaffold with bottom bar
    // Use Scaffold composable
    // Add bottomBar with NavigationBar
    // Add NavHost in the content
}

// TODO: Step 4 - Implement screen composables
// Create these composables:

/**
 * HomeScreen - Displays home content with navigation
 * @param navController for navigation to detail screen
 */
@Composable
fun HomeScreen(navController: NavHostController) {
    // TODO: Implement home screen
    // Show title and ONE button that navigates to Detail screen
    // Example: navController.navigate("detail/SampleItem")
}

/**
 * ProfileScreen - Displays user profile
 */
@Composable
fun ProfileScreen() {
    // TODO: Implement profile screen
    // Show simple profile information
}

/**
 * SettingsScreen - Displays app settings
 */
@Composable
fun SettingsScreen() {
    // TODO: Implement settings screen
    // Show simple settings options
}

/**
 * DetailScreen - Shows details for an item
 * @param itemId The ID passed from navigation
 */
@Composable
fun DetailScreen(itemId: String?) {
    // TODO: Implement detail screen
    // Display the item information using the itemId parameter
}

// TODO: Step 5 - Implement bottom navigation bar
/**
 * BottomNavigationBar - Bottom nav bar with Home, Profile, Settings tabs
 * @param navController for navigation between tabs
 */
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    // TODO: Implement bottom navigation bar
    // Use NavigationBar with NavigationBarItem
    // Items: Home (Icons.Default.Home), Profile (Icons.Default.Person), Settings (Icons.Default.Settings)
    // Track selected state based on current route
    // Navigate on item click
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    MaterialTheme {
        BottomNavigationApp()
    }
}
