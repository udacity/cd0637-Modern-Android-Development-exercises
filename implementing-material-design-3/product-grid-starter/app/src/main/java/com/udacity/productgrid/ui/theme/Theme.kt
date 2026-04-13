package com.udacity.productgrid.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Light color scheme for the app.
 * 
 * TODO: Step 1 - Define the light color scheme
 * Use lightColorScheme() and set the following colors:
 * 
 * Main colors:
 * - primary = Color(0xFF6200EE)
 * - onPrimary = Color.White
 * - secondary = Color(0xFF03DAC6)
 * - onSecondary = Color.Black
 * 
 * Background and surface:
 * - background = Color(0xFFFFFBFE)
 * - onBackground = Color(0xFF1C1B1F)
 * - surface = Color(0xFFFFFBFE)
 * - onSurface = Color(0xFF1C1B1F)
 * 
 * Hint: The pattern is:
 * private val LightColorScheme = lightColorScheme(
 *     primary = Color(...),
 *     onPrimary = Color(...),
 *     // ... more colors
 * )
 */
private val LightColorScheme = lightColorScheme(
    // TODO: Add light theme colors here
)

/**
 * Dark color scheme for the app.
 * 
 * TODO: Step 2 - Define the dark color scheme
 * Use darkColorScheme() and set the following colors:
 * 
 * Main colors:
 * - primary = Color(0xFFBB86FC)
 * - onPrimary = Color(0xFF3700B3)
 * - secondary = Color(0xFF03DAC6)
 * - onSecondary = Color.Black
 * 
 * Background and surface:
 * - background = Color(0xFF1C1B1F)
 * - onBackground = Color(0xFFE6E1E5)
 * - surface = Color(0xFF1C1B1F)
 * - onSurface = Color(0xFFE6E1E5)
 * 
 * Hint: Dark themes use lighter colors for primary/secondary to ensure
 * good contrast against the dark background.
 */
private val DarkColorScheme = darkColorScheme(
    // TODO: Add dark theme colors here
)

/**
 * AppTheme composable that applies the Material3 theme to the app.
 * 
 * TODO: Step 3 - Implement the theme composable
 * 
 * This composable should:
 * 1. Select the appropriate color scheme based on darkTheme parameter
 * 2. Wrap the content with MaterialTheme, passing the color scheme
 * 
 * Hint:
 * val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
 * MaterialTheme(
 *     colorScheme = colorScheme,
 *     content = content
 * )
 * 
 * @param darkTheme Whether to use dark theme. Defaults to system setting.
 * @param content The composable content to theme.
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // TODO: Implement theme logic here
    // Select color scheme based on darkTheme
    // Apply MaterialTheme with the selected color scheme
}
