# Demo: Building Your First Composable - Greeting Card

## Overview
This demo project showcases the creation of a personalized **Greeting Card** using Jetpack Compose. Building upon the concepts of the Profile Card, it demonstrates how to combine multiple composable functions to create a visually appealing and interactive UI component.

## Key Features
- **Custom Composable**: The `GreetingCard` function is a reusable component that displays a recipient's name, a heartfelt message, and a sender's signature.
- **Material Design 3**: Utilizes Material3 `Card`, `Button`, and `Typography` for a modern, consistent look.
- **Advanced Styling**: Demonstrates the use of `Brush.verticalGradient` for a beautiful background and `RoundedCornerShape` for styled containers.
- **Interactive Elements**: Includes a "Send Wishes" button with a callback listener.

## Implementation Details

### GreetingCard Composable
- **Card**: Provides a polished container with elevation and rounded corners.
- **Column**: Arranges the greeting elements (Icon, Header, Message, Footer) vertically with consistent spacing.
- **Icon**: Uses the `Star` icon from Material Icons.
- **Typography**: Employs `headlineSmall` for the greeting and `bodyLarge` for the message to ensure clear visual hierarchy.

### Theming
The project includes a custom `GreetingAppTheme` using a calm **Teal and Mint** palette for a professional, neutral aesthetic.

## How to Run
1. Open the project in Android Studio.
2. Sync the Gradle files.
3. Run the app on an emulator or physical device.
4. You will see a star-themed greeting card addressed to "Alex".

## Learning Objectives
- Understanding the structure of a Composable function.
- Using `Box`, `Column`, and `Padding` for layout management.
- Applying `Modifiers` for backgrounds, shapes, and sizes.
- Implementing Material Design 3 components effectively.
- Previewing UI components during development.
