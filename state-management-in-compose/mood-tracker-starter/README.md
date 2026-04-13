# Exercise 03: Mood Tracker - Managing Compose State

## Learning Objective
Manage UI state using Compose state APIs and state hoisting

## Duration
20-30 minutes

## Overview
In this exercise, you'll build a mood tracker that allows users to select their current mood. This hands-on practice will help you understand how to manage state in Compose, implement state hoisting, and create stateful and stateless composables.

## What You'll Build
A mood tracker that includes:
- A row of mood buttons with emoji (😊 Happy, 😐 Neutral, 😢 Sad, 😡 Angry, 🎉 Excited)
- Visual indication of the selected mood (different background color)
- A message that changes based on the selected mood
- State hoisting pattern (parent manages state, children are stateless)

## Skills Practiced
- Using `remember` and `mutableStateOf`
- State hoisting principles
- Creating stateful vs stateless composables
- Handling click events with state updates
- Conditional UI based on state
- State best practices

## Instructions

### Step 1: Review the Project Structure
Open `MainActivity.kt`. You'll see the basic structure with a `MoodTrackerScreen` composable that needs implementation.

### Step 2: Implement State Management
In the `MoodTrackerScreen` composable:

1. **Create state for selected mood**
   - Use `remember` and `mutableStateOf` to track the selected mood
   - Initialize it with a default value (e.g., "Happy")

2. **Create the mood options list**
   - Define a list of mood options with emoji and labels
   - Consider using a data class or Pair

### Step 3: Build the UI Layout
Create the mood tracker interface:

1. **Display the selected mood message**
   - Show text like "You're feeling [mood]!" 
   - Use the current state value

2. **Create a Column of mood buttons**
   - Use `Column` with proper spacing (Arrangement.spacedBy)
   - For each mood option, create a button that:
     - Displays the emoji and label side by side
     - Changes background color when selected
     - Updates the state when clicked

3. **Implement the MoodButton composable** (stateless)
   - Accept parameters: emoji, label, isSelected, onClick
   - Display the mood with appropriate styling (horizontal layout)
   - Show different background when selected vs unselected
   - Make button wide enough to show full text (fillMaxWidth with fraction)

### Step 4: Practice State Hoisting
- Keep state in `MoodTrackerScreen` (parent)
- Pass state values and callbacks down to `MoodButton` (child)
- `MoodButton` should be completely stateless

### Step 5: Test Your Implementation
1. Use the preview function to see your work
2. Run the app on an emulator or device
3. Click different moods and verify:
   - The message updates correctly
   - Only one mood is selected at a time
   - Visual feedback is clear

## Expected Result
When complete, you should see:
- Five mood buttons in a row with emoji
- The selected mood highlighted with a different background
- A message above showing the current mood
- Clicking a mood updates both the message and visual selection

## Tips
- Use `remember { mutableStateOf() }` to create state that survives recomposition
- State hoisting means the parent owns the state, children just display and report events
- Use conditional styling: `background(if (isSelected) Color else Color)`
- Row's `horizontalArrangement = Arrangement.SpaceEvenly` helps distribute buttons
- Keep composables focused: stateful for logic, stateless for UI

## Resources
- [State in Compose](https://developer.android.com/jetpack/compose/state)
- [State Hoisting](https://developer.android.com/jetpack/compose/state-hoisting)
- [Thinking in Compose](https://developer.android.com/jetpack/compose/mental-model)

## Need Help?
If you get stuck:
1. Remember: state should be in the lowest common ancestor
2. Stateless composables receive everything through parameters
3. Use by `remember { mutableStateOf() }` for state delegation
4. Check that your MoodButton is truly stateless (no state inside it)

Good luck! 🚀
