# Exercise 04 Solution: Product Card Grid - Material Design 3

## Solution Overview
This is the complete solution for Exercise 04. The Product Grid demonstrates:
- Custom Material3 theme implementation
- Light and dark color schemes
- Dynamic theme switching
- LazyVerticalGrid for grid layouts
- Material3 Card components with elevation
- Typography system usage
- Proper Material Design 3 patterns

## Key Implementation Details

### Theme.kt (Custom Theme)
- Defines `LightColorScheme` and `DarkColorScheme` using Material3 builders
- Creates `AppTheme` composable that switches between schemes
- Uses `MaterialTheme` to provide colors, typography, and shapes throughout the app

### Product Data Class
- Simple data model for products
- Uses emoji as image placeholders (real apps would use actual images)

### ProductGridScreen (Stateful)
- Manages dark theme state
- Provides theme toggle UI
- Displays products in a grid using LazyVerticalGrid

### ProductCard (Stateless)
- Material3 Card with proper elevation
- Uses theme colors and typography
- Responsive to theme changes

## Material Design 3 Features Demonstrated
- **Color Schemes**: Custom light and dark palettes
- **Typography**: Multiple text styles (headlineMedium, titleMedium, bodyMedium)
- **Elevation**: Automatic card elevation with tonal surfaces
- **Components**: Card, Switch, Surface
- **Dynamic Theming**: Smooth transition between light/dark modes

## File Structure
```
app/src/main/java/com/udacity/productgrid/
├── MainActivity.kt          # Entry point and ProductGridScreen
└── ui/theme/
    └── Theme.kt            # Custom theme with color schemes
```

## Running the Solution
1. Open this project in Android Studio
2. Sync the Gradle files
3. Run the app on an emulator or device
4. Toggle the dark mode switch to see theme changes
5. Observe how cards, text, and background colors update

## Learning Points
- Material3 uses `lightColorScheme()` and `darkColorScheme()` builders
- Theme wraps the entire app to provide consistent styling
- Cards automatically get elevation with tonal surfaces in Material3
- Typography should come from MaterialTheme, not hardcoded sizes
- Color should come from MaterialTheme.colorScheme for proper theming
