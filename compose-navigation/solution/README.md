# Exercise 05 Solution: Bottom Navigation - Compose Navigation

## Solution Overview
This is the complete solution for Exercise 05. The Bottom Navigation app demonstrates:
- NavHost and NavController setup
- Multiple navigation routes
- Bottom navigation with NavigationBar
- Navigation between screens
- Passing arguments through navigation
- Conditional UI based on navigation state
- Proper back stack handling

## Key Implementation Details

### Navigation Routes
Uses a sealed class `Screen` to define type-safe routes:
- Reduces string typos
- Makes routes reusable
- Easier to refactor

### NavHost Setup
- Created with `rememberNavController()`
- Defines all composable destinations
- Handles argument extraction for detail screen
- Start destination set to Home

### Bottom Navigation
- Uses Scaffold with bottomBar
- NavigationBar with 3 NavigationBarItem components
- Tracks selected state using current back stack entry
- Only shows on main tabs (hidden on detail screen)

### Screen Navigation
- HomeScreen navigates to DetailScreen with item ID
- Tab clicks navigate to respective screens
- Back button properly returns to previous screen

## Architecture Patterns Demonstrated
- **Separation of Concerns**: Routes defined separately from UI
- **Composable Screens**: Each screen is an independent composable
- **State Hoisting**: NavController passed down as needed
- **Conditional Rendering**: Bottom bar shown/hidden based on route

## File Structure
```
app/src/main/java/com/udacity/bottomnav/
└── MainActivity.kt     # All navigation and screen code
```

## Running the Solution
1. Open this project in Android Studio
2. Sync the Gradle files
3. Run the app on an emulator or device
4. Test:
   - Tab switching in bottom navigation
   - Navigation from Home to Detail
   - Back button behavior
   - Bottom bar visibility

## Learning Points
- NavHost requires navController and startDestination
- Use `rememberNavController()` to create NavController
- Navigate with `navController.navigate(route)`
- Extract arguments from `backStackEntry.arguments`
- Track current route with `currentBackStackEntryAsState()`
- Scaffold provides consistent app structure with bottom bar
- Bottom navigation is common in Android apps with multiple sections

## Navigation Flow
```
Home Screen ─┬─> Detail Screen (with argument)
             │
Profile Screen
             │
Settings Screen
```
