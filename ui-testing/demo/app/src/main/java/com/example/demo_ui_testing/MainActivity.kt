package com.example.demo_ui_testing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demo_ui_testing.ui.DashboardScreen
import com.example.demo_ui_testing.ui.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen { username ->
                navController.navigate("dashboard/$username")
            }
        }
        composable("dashboard/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Guest"
            DashboardScreen(username)
        }
    }
}
