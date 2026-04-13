# Exercise 06 Solution: Timer with Cleanup - Advanced Compose Patterns

## Solution Overview
This is the complete solution for Exercise 06. The Timer demonstrates:
- LaunchedEffect for running coroutines tied to composition
- DisposableEffect for cleanup when leaving composition
- Proper side effect management
- State management with timers
- Coroutine delays and loops

## Key Implementation Details

### LaunchedEffect
- Key parameter: `isRunning` - effect restarts when this changes
- Cancels previous coroutine when key changes
- Uses `while(isRunning && timeRemaining > 0)` loop
- `delay(1000)` for 1-second intervals
- Updates state inside the coroutine

### DisposableEffect
- Runs once on composition (key = Unit)
- Logs when screen appears
- `onDispose` block logs cleanup
- Demonstrates proper cleanup pattern

### State Management
- `timeRemaining` - countdown value
- `isRunning` - whether timer is active
- Both use `mutableStateOf` with `remember`

### Timer Logic
- LaunchedEffect automatically cancels when `isRunning` becomes false
- Timer stops when reaching 0
- Reset button restores initial time and stops timer

## Side Effects Demonstrated
**LaunchedEffect**: 
- Tied to composition lifecycle
- Cancels when key changes
- Perfect for ongoing work like timers

**DisposableEffect**:
- Cleanup when composable leaves composition
- Critical for releasing resources
- Prevents memory leaks

## File Structure
```
app/src/main/java/com/udacity/timer/
└── MainActivity.kt     # Timer screen with side effects
```

## Running the Solution
1. Open this project in Android Studio
2. Sync the Gradle files
3. Run the app on an emulator or device
4. Click Start to begin countdown
5. Pause and Resume to see effect restart
6. Check Logcat for DisposableEffect logs

## Learning Points
- LaunchedEffect(key) runs on composition and when key changes
- Previous LaunchedEffect coroutine cancels when key changes
- DisposableEffect must have onDispose block
- Use delay() for timed operations in coroutines
- Side effects are powerful but must be used carefully
- Always consider cleanup when using effects
