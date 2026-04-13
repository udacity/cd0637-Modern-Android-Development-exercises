# Exercise 05: Bottom Navigation - Compose Navigation

## Learning Objective
Implement navigation in Compose using Navigation Compose library

## Duration
20-30 minutes

## Overview
In this exercise, you'll build a simple multi-screen app with bottom navigation. You'll learn how to set up navigation in Compose, create routes, and implement a bottom navigation bar - one of the most common patterns in Android apps.

## What You'll Build
A multi-screen app featuring:
- Bottom navigation bar with 3 tabs (Home, Profile, Settings)
- NavHost to manage screen navigation
- Three simple screen composables
- Navigation from Home to Detail screen (with argument passing)
- Proper navigation between tabs

## Skills Practiced
- NavHost and NavController setup
- Creating navigation routes
- Bottom navigation implementation
- Navigating between tabs
- Passing simple arguments between screens
- Scaffold with bottom bar

## Instructions

### Step 1: Review the Project Structure
Open `MainActivity.kt` and review the setup. The Navigation Compose dependency is already added.

### Step 2: Define Navigation Routes
Create route constants for your screens:
- Routes needed: "home", "profile", "settings", "detail/{itemId}"
- Can use simple string constants or a sealed class

### Step 3: Create Screen Composables
Implement simple screen composables:

1. **HomeScreen** - Display title and ONE button that navigates to Detail
2. **ProfileScreen** - Display profile content
3. **SettingsScreen** - Display settings content  
4. **DetailScreen** - Display details with the passed argument

### Step 4: Set Up NavHost
In the main composable:

1. Create NavController using `rememberNavController()`
2. Set up NavHost with:
   - NavController
   - Start destination (home)
   - Composable destinations for each route
   - Detail route with argument handling

### Step 5: Implement Bottom Navigation
1. Use Scaffold with `bottomBar`
2. Create NavigationBar with NavigationBarItem for each tab
3. Highlight selected tab based on current route
4. Navigate to appropriate screen on tab click

### Step 6: Test Navigation
1. Run the app
2. Test tab switching
3. Verify correct screens display

## Expected Result
When complete, you should see:
- Bottom navigation with 3 tabs
- Home, Profile, and Settings screens
- Smooth navigation between tabs
- Selected tab highlighted

## Tips
- Use `rememberNavController()` to create NavController
- NavHost takes a `startDestination` parameter
- NavigationBarItem has `selected`, `onClick`, `icon`, and `label` parameters
- Get current route with `navBackStackEntry?.destination?.route`
- Navigate with `navController.navigate("route")`
- Pass arguments: `navController.navigate("detail/itemValue")`
- Extract arguments: `backStackEntry.arguments?.getString("itemId")`
- Use `popUpTo` and `launchSingleTop` to prevent back stack buildup

## Resources
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- [Bottom Navigation](https://developer.android.com/jetpack/compose/layouts/material#bottom-navigation)

## Need Help?
If you get stuck:
1. Make sure the navigation dependency is added in build.gradle
2. NavHost needs both navController and startDestination
3. Bottom navigation items need both routes and icons
4. Use navController.currentBackStackEntryAsState() for selected state
5. Remember to handle the back stack properly

Good luck! 🚀
