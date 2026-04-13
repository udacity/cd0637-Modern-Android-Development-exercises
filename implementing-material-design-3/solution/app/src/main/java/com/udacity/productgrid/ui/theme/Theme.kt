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
 * Material Design 3 uses a systematic approach to color with semantic roles:
 * - primary: Main brand color used for key components
 * - secondary: Supporting color for less prominent components
 * - tertiary: Accent color for highlights
 * - background: Color of the background surface
 * - surface: Color of elevated surfaces like cards
 * - error: Color for error states
 * 
 * Each color has an "on" variant (onPrimary, onSecondary, etc.) for content
 * displayed on top of that color to ensure proper contrast.
 */
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFBB86FC),
    onPrimaryContainer = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF018786),
    onSecondaryContainer = Color.White,
    tertiary = Color(0xFFFF6F00),
    onTertiary = Color.White,
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F),
    error = Color(0xFFB00020),
    onError = Color.White
)

/**
 * Dark color scheme for the app.
 * 
 * Dark theme uses darker base colors with lighter accents.
 * The surface colors use tonal elevation to create depth - elevated surfaces
 * get lighter tones rather than traditional shadows.
 */
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),
    onPrimary = Color(0xFF3700B3),
    primaryContainer = Color(0xFF6200EE),
    onPrimaryContainer = Color(0xFFE1BEE7),
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF005048),
    onSecondaryContainer = Color(0xFF70F5E3),
    tertiary = Color(0xFFFFAB00),
    onTertiary = Color.Black,
    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0),
    error = Color(0xFFCF6679),
    onError = Color.Black
)

/**
 * AppTheme composable that applies the Material3 theme to the app.
 * 
 * This is the entry point for theming. It wraps the entire app and provides
 * access to colors, typography, and shapes through MaterialTheme.
 * 
 * @param darkTheme Whether to use dark theme. Defaults to system setting.
 * @param content The composable content to theme.
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Select color scheme based on theme
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    // Apply Material3 theme with the selected color scheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = androidx.compose.material3.Typography(), // Default Material3 typography
        content = content
    )
}
