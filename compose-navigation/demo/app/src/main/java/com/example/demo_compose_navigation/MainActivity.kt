package com.example.demo_compose_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.demo_compose_navigation.ui.theme.Demo02Theme
import kotlinx.serialization.Serializable

data class BottomNavItem<T : Any>(
    val route: T,
    val icon: ImageVector,
    val label: String
)

/**
 * Football Data Model
 */
data class Team(
    val id: Int,
    val name: String,
    val nickname: String,
    val stadium: String,
    val color: Color,
    val description: String
)

object MockData {
    val teams = listOf(
        Team(
            1, "Arsenal", "The Gunners", "Emirates Stadium", Color(0xFFEF0107),
            "A historic club playing fluid attacking football under Mikel Arteta, contending for major honors."
        ),
        Team(
            2, "Manchester City", "Cityzens", "Etihad Stadium", Color(0xFF6CABDD),
            "Consistently dominant in recent years, known for their tactical precision and star-studded squad."
        ),
        Team(
            3, "Liverpool", "The Reds", "Anfield", Color(0xFFC8102E),
            "Famous for their 'You'll Never Walk Alone' anthem and high-intensity pressing game."
        ),
        Team(
            4, "Aston Villa", "The Villans", "Villa Park", Color(0xFF95BFE5),
            "One of the oldest clubs in England, experiencing a major resurgence in the European spots."
        ),
        Team(
            5, "Tottenham", "Spurs", "Tottenham Hotspur Stadium", Color(0xFF132257),
            "Boasting one of the world's most modern stadiums and a tradition of entertaining football."
        )
    )
}

/**
 * Type-Safe Navigation Destinations
 */
@Serializable
sealed class Screen {
    @Serializable
    object TeamsList : Screen()

    @Serializable
    data class TeamDetail(val teamId: Int) : Screen()

    @Serializable
    object Standings : Screen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Demo02Theme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavItem(Screen.TeamsList, Icons.AutoMirrored.Filled.List, "Teams"),
        BottomNavItem(Screen.Standings, Icons.Default.Star, "Standings")
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, bottomNavItems)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.TeamsList,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Screen.TeamsList> {
                TeamsScreen(navController)
            }
            composable<Screen.TeamDetail> { backStackEntry ->
                val detail: Screen.TeamDetail = backStackEntry.toRoute()
                TeamDetailScreen(navController, detail.teamId)
            }
            composable<Screen.Standings> {
                StandingsScreen()
            }
        }
    }
}

@Composable
fun TeamsScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(
            "Premier League Teams",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(MockData.teams) { team ->
                TeamItem(team) {
                    navController.navigate(Screen.TeamDetail(team.id))
                }
            }
            item{
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun TeamItem(team: Team, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier.size(40.dp),
                color = team.color,
                shape = MaterialTheme.shapes.small
            ) {}
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    team.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(team.nickname, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
    }
}

@Composable
fun TeamDetailScreen(navController: NavHostController, teamId: Int) {
    val team = MockData.teams.find { it.id == teamId } ?: return
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)) {
        Text(
            team.name,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.ExtraBold
        )
        Text(team.nickname, style = MaterialTheme.typography.headlineSmall, color = team.color)
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        DetailRow("Stadium", team.stadium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "About the Club",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(team.description, style = MaterialTheme.typography.bodyLarge)
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(16.dp)
        )
        Button(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Teams")
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, fontWeight = FontWeight.Bold)
        Text(value)
    }
}

@Composable
fun StandingsScreen() {
    val pointsList = remember {
        val startPoints = 90
        val points = mutableListOf<Int>()
        points.add(startPoints)
        points.add(startPoints - 10) // 10 point difference for the first two
        for (i in 2 until MockData.teams.size) {
            points.add(points[i - 1] - kotlin.random.Random.nextInt(1, 4))
        }
        points
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "League Table",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Pos", fontWeight = FontWeight.Bold)
                    Text(
                        "Team",
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text("Pts", fontWeight = FontWeight.Bold)
                }
                MockData.teams.forEachIndexed { index, team ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${index + 1}")
                        Text(
                            team.name,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp)
                        )
                        val points = pointsList.getOrElse(index) { 0 }
                        Text("$points")
                    }
                }
            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController, items: List<BottomNavItem<out Any>>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            val isSelected =
                currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
