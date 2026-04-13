package com.udacity.bottomnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

/**
 * Sealed class defining navigation routes.
 * 
 * Using a sealed class for routes provides type safety and makes it easier
 * to manage routes as the app grows.
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object Detail : Screen("detail/{itemId}") {
        fun createRoute(itemId: String) = "detail/$itemId"
    }
}

/**
 * Data class for bottom navigation items.
 * Helps organize navigation bar configuration.
 */
data class BottomNavItem(
    val screen: Screen,
    val icon: ImageVector,
    val label: String
)

/**
 * MainActivity is the entry point of the application.
 */
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

/**
 * BottomNavigationApp - Main app composable with navigation setup.
 * 
 * This composable demonstrates:
 * - NavController creation and management
 * - Scaffold with bottom navigation
 * - NavHost with multiple routes
 * - Conditional bottom bar visibility
 */
@Composable
fun BottomNavigationApp() {
    // Create NavController - this manages navigation state
    // rememberNavController() ensures it survives recomposition
    val navController = rememberNavController()
    
    // Track the current navigation destination
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    // Define bottom navigation items
    val bottomNavItems = listOf(
        BottomNavItem(Screen.Home, Icons.Default.Home, stringResource(R.string.home_tab)),
        BottomNavItem(Screen.Profile, Icons.Default.Person, stringResource(R.string.profile_tab)),
        BottomNavItem(Screen.Settings, Icons.Default.Settings, stringResource(R.string.settings_tab))
    )
    
    // Scaffold provides consistent app structure
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavItems,
                navController = navController,
                currentRoute = currentRoute
            )
        }
    ) { paddingValues ->
        // NavHost defines all navigation routes and their composables
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            // Home screen route
            composable(Screen.Home.route) {
                HomeScreen(navController)
            }
            
            // Profile screen route
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            
            // Settings screen route
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
            
            // Detail screen route with argument
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("itemId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId")
                DetailScreen(itemId)
            }
        }
    }
}

/**
 * HomeScreen - Displays home content with navigation.
 * 
 * Demonstrates navigation to another screen with an argument.
 * 
 * @param navController for navigating to detail screen
 */
@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.home_title),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "🏠",
            fontSize = 64.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Welcome Home!",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Button to navigate to detail screen with an argument
        Button(
            onClick = {
                navController.navigate(Screen.Detail.createRoute("Sample Item"))
            }
        ) {
            Text("View Details")
        }
    }
}

/**
 * ProfileScreen - Displays user profile information.
 * 
 * Simple screen demonstrating a tab destination.
 */
@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.profile_title),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "👤",
            fontSize = 64.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "John Doe",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        
        Text(
            text = "john.doe@example.com",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * SettingsScreen - Displays app settings.
 * 
 * Simple screen demonstrating another tab destination.
 */
@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.settings_title),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "⚙️",
            fontSize = 64.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "App Settings",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Configure your preferences here",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * DetailScreen - Shows details for an item.
 * 
 * Demonstrates receiving navigation arguments.
 * 
 * @param itemId The ID passed from navigation
 */
@Composable
fun DetailScreen(itemId: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.detail_title),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "📄",
            fontSize = 64.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Details for:",
            fontSize = 16.sp
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = itemId ?: "Unknown",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

/**
 * BottomNavigationBar - Bottom navigation bar component.
 * 
 * Demonstrates:
 * - NavigationBar with multiple items
 * - Selected state tracking
 * - Navigation on item click
 * 
 * @param items List of bottom navigation items to display
 * @param navController for navigation between tabs
 * @param currentRoute current navigation route for highlighting selected tab
 */
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    currentRoute: String?
) {
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                // Highlight the item if it's the current route
                selected = currentRoute == item.screen.route,
                onClick = {
                    // Navigate to the screen, avoiding duplicates in back stack
                    navController.navigate(item.screen.route) {
                        // Pop up to the start destination to avoid building large back stack
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(item.label)
                }
            )
        }
    }
}

/**
 * Preview function for the app.
 */
@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    MaterialTheme {
        BottomNavigationApp()
    }
}
