# Exercise 04: Product Card Grid - Material Design 3

## Learning Objective
Implement Material Design 3 components and theming

## Duration
25-35 minutes

## Overview
In this exercise, you'll build a product catalog using Material Design 3 components. You'll create a custom theme, implement a light/dark mode toggle, and display products in a grid layout with proper Material3 styling.

## What You'll Build
A product catalog featuring:
- A grid of product cards using LazyVerticalGrid
- Custom Material3 color scheme
- Light and dark theme support
- Theme toggle button
- Product cards with elevation, colors, and typography
- Material3 Card, Surface, and other components

## Skills Practiced
- Material3 components (Card, Button, Surface, Switch)
- Custom MaterialTheme setup
- Color schemes (light and dark)
- Typography system
- Surface tonal elevation
- Shape system
- Dynamic theming

## Instructions

### Step 1: Review the Project Structure
Open `MainActivity.kt` and `ui/theme/Theme.kt`. You'll see:
- Theme.kt with TODO comments for implementing color schemes
- MainActivity with basic structure

### Step 2: Implement Custom Theme
Open `ui/theme/Theme.kt` and complete the TODOs:

1. **Define LightColorScheme**
   - Use `lightColorScheme()` builder
   - Set primary, onPrimary, secondary, background, surface colors
   - Follow the color values in the TODO comments

2. **Define DarkColorScheme**
   - Use `darkColorScheme()` builder
   - Set colors appropriate for dark theme
   - Use lighter colors for primary/secondary on dark backgrounds

3. **Implement AppTheme composable**
   - Select color scheme based on `darkTheme` parameter
   - Wrap content with `MaterialTheme` and pass the color scheme

### Step 3: Implement Product Data
1. **Create a Product data class**
   - Fields: id, name, price, emoji (as image placeholder)
   
2. **Create sample product list**
   - At least 6-8 products for the grid

### Step 4: Build the Product Grid UI
In `ProductGridScreen`:

1. **Add theme toggle**
   - State for dark mode (remember/mutableStateOf)
   - Row with text and Switch component
   
2. **Implement LazyVerticalGrid**
   - Use `GridCells.Fixed(2)` for 2 columns
   - Display ProductCard for each product
   
3. **Create ProductCard composable**
   - Use Material3 Card component
   - Show product emoji, name, and price
   - Apply proper elevation and colors
   - Use Material3 typography

### Step 5: Apply Material Design
- Use MaterialTheme.colorScheme for colors
- Use MaterialTheme.typography for text styles
- Apply Card elevation
- Use proper surface colors
- Implement spacing and padding from theme

### Step 6: Test Themes
1. Toggle between light and dark themes
2. Verify colors update correctly
3. Check card elevation visibility
4. Ensure typography is readable in both themes

## Expected Result
When complete, you should see:
- A grid of product cards (2 columns)
- Theme toggle switch at the top
- Cards with elevation and proper Material3 styling
- Smooth transition between light and dark themes
- Proper color contrast in both themes

## Tips
- Use `lightColorScheme()` and `darkColorScheme()` from Material3
- Card elevation is automatic in Material3 (no need to specify manually)
- Access theme colors with `MaterialTheme.colorScheme.primary`
- Access typography with `MaterialTheme.typography.titleMedium`
- LazyVerticalGrid is like LazyColumn but with columns
- Use `Modifier.padding()` from the theme's spacing

## Resources
- [Material Design 3](https://m3.material.io/)
- [Material3 in Compose](https://developer.android.com/jetpack/compose/designsystems/material3)
- [Color Schemes](https://developer.android.com/jetpack/compose/designsystems/material3#color-scheme)
- [Typography](https://developer.android.com/jetpack/compose/designsystems/material3#typography)

## Need Help?
If you get stuck:
1. Start with the Theme.kt file - this is the foundation
2. Use the Material3 color scheme builder for custom colors
3. LazyVerticalGrid syntax is similar to LazyColumn
4. Remember to wrap your app with your custom theme composable

Good luck! 🚀
