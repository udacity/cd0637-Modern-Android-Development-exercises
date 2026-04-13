# Exercise 01: Profile Card - Building Your First Composables

## Learning Objective
Create basic UI elements using composable functions

## Duration
15-20 minutes

## Overview
In this exercise, you'll build a profile card that displays user information. This hands-on practice will help you understand how to create and compose basic UI elements using Jetpack Compose.

## What You'll Build
A profile card that includes:
- A profile image (using an icon as placeholder)
- Name text
- Bio/description text
- A contact button
- Preview function to see your work

## Skills Practiced
- Creating composable functions
- Using Text composable
- Using Button composable
- Using Image composable
- Creating preview functions
- Passing parameters to composables
- Following function naming conventions
- Basic composition (combining composables)

## Instructions

### Step 1: Review the Project Structure
Open `MainActivity.kt` and familiarize yourself with the basic structure. Notice the `setContent` block where Compose UI is rendered.

### Step 2: Build the ProfileCard Composable
The ProfileCard has a Column layout ready. Find the TODO comments and add the missing UI elements:

1. **Add a profile icon** using the Icon composable
   - Use `Icons.Default.Person` as the imageVector
   - Set `contentDescription = stringResource(R.string.profile_picture_description)`
   - Add `modifier = Modifier.size(100.dp)` to make it large
   - Add `tint = Color(0xFF6200EE)` for a purple color

2. **Add the name text** using the Text composable
   - Display the `name` parameter
   - Add `style = MaterialTheme.typography.headlineMedium`
   - Add `fontWeight = FontWeight.Bold`
   - Add `modifier = Modifier.padding(top = 16.dp)` for spacing

3. **Add the bio text** using the Text composable
   - Display the `bio` parameter
   - Add `style = MaterialTheme.typography.bodyLarge`
   - Add `textAlign = TextAlign.Center`
   - Add `modifier = Modifier.padding(top = 8.dp, start = 24.dp, end = 24.dp)`

4. **Add spacing to the button**
   - The Button is already provided, but needs proper spacing
   - Add `modifier = Modifier.padding(top = 16.dp)` to the Button

### Step 3: Test Your Implementation
1. Use the preview function `ProfileCardPreview()` to see your work
2. Run the app on an emulator or device
3. Verify all elements appear correctly

## Expected Result
When complete, you should see a profile card with:
- A large person icon at the top
- "John Doe" as the name in bold, large text
- A bio text centered below the name
- A "Contact" button at the bottom

## Tips
- Remember that composable function names should start with a capital letter
- Use `Modifier` to customize the appearance and behavior of composables
- The preview function helps you see changes without running the app
- Don't forget to add spacing between elements using `padding()`

## Resources
- [Jetpack Compose Basics](https://developer.android.com/jetpack/compose/tutorial)
- [Material Design 3 Components](https://developer.android.com/jetpack/compose/designsystems/material3)
- [Compose Layout Basics](https://developer.android.com/jetpack/compose/layouts/basics)

## Need Help?
If you get stuck:
1. Check the TODO comments for specific guidance
2. Review the preview function to understand the expected parameters
3. Make sure you're using the correct Material Design 3 composables
4. Verify your imports include Jetpack Compose libraries

Good luck! 🚀
