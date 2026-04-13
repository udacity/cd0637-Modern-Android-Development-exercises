# Exercise 06: Timer with Cleanup - Advanced Compose Patterns

## Learning Objective
Apply advanced Compose techniques including side effects and lifecycle management

## Duration
25-30 minutes

## Overview
In this exercise, you'll build a countdown timer that demonstrates important side effect concepts in Compose. You'll learn how to use LaunchedEffect to run coroutines tied to composition, and DisposableEffect to clean up when the composable leaves composition.

## What You'll Build
A countdown timer app featuring:
- Countdown timer that runs automatically
- Start, Pause, and Reset controls
- LaunchedEffect for timer logic
- DisposableEffect for cleanup
- Proper state management
- Side effect handling

## Skills Practiced
- LaunchedEffect for coroutine lifecycle
- DisposableEffect for cleanup
- rememberCoroutineScope for manual coroutine control
- Side effect management
- State with timers
- Coroutine delays

## Instructions

### Step 1: Review the Project Structure
Open `MainActivity.kt` and review the basic structure provided.

### Step 2: Set Up Timer State
Create state variables for:
1. Time remaining (in seconds)
2. Timer running state (Boolean)
3. Initial time value

Use `remember { mutableStateOf() }` for state management.

### Step 3: Implement LaunchedEffect
Use LaunchedEffect to:
1. Run when the timer is running
2. Decrement time every second using `delay(1000)`
3. Stop when time reaches 0
4. Restart effect when running state changes

Key: LaunchedEffect cancels when its key changes!

### Step 4: Add DisposableEffect
Implement DisposableEffect to:
1. Log when the timer screen appears
2. Clean up in the `onDispose` block
3. Demonstrate proper cleanup patterns

### Step 5: Create Timer Controls
Implement buttons for:
1. **Start/Pause** - Toggle running state
2. **Reset** - Reset to initial time and stop

### Step 6: Display Timer
Show the countdown in MM:SS format.

### Step 7: Test Side Effects
1. Run the app and start the timer
2. Pause and resume
3. Reset the timer
4. Navigate away and back (if you've implemented navigation)
5. Verify cleanup happens in logs

## Expected Result
When complete, you should see:
- Countdown timer displaying MM:SS format
- Start/Pause button that works correctly
- Reset button that resets to initial time
- Timer that counts down every second
- Proper cleanup when composable disposes

## Tips
- LaunchedEffect(key) runs when the composable enters composition and when key changes
- LaunchedEffect cancels the previous coroutine when key changes
- Use `delay(1000)` for 1-second intervals
- DisposableEffect must call `onDispose { }` in its block
- Format time: `val minutes = timeInSeconds / 60; val seconds = timeInSeconds % 60`
- Use `String.format("%02d:%02d", minutes, seconds)` for formatting

## Resources
- [Side Effects in Compose](https://developer.android.com/jetpack/compose/side-effects)
- [LaunchedEffect](https://developer.android.com/jetpack/compose/side-effects#launchedeffect)
- [DisposableEffect](https://developer.android.com/jetpack/compose/side-effects#disposableeffect)

## Need Help?
If you get stuck:
1. LaunchedEffect needs a key parameter - use the running state
2. Don't forget to check if time > 0 before decrementing
3. DisposableEffect always needs an onDispose block
4. Use a while loop inside LaunchedEffect for continuous countdown
5. Remember to update state immutably

Good luck! 🚀
