# Exercise 03 Solution: Mood Tracker - Managing Compose State

## Solution Overview
This is the complete solution for Exercise 03. The Mood Tracker demonstrates:
- Using `remember` and `mutableStateOf` for state management
- State hoisting pattern (parent manages state, children are stateless)
- Creating stateful vs stateless composables
- Handling user interactions that update state
- Conditional UI based on state
- Avoiding unnecessary recomposition

## Key Implementation Details

### MoodTrackerScreen (Stateful Composable)
- Owns the `selectedMood` state using `remember { mutableStateOf() }`
- Uses `by` keyword for property delegation (cleaner syntax)
- Passes state down to child composables
- Passes callbacks (onClick) to handle state changes

### MoodButton (Stateless Composable)
- Completely stateless - receives everything through parameters
- `isSelected` determines visual styling
- `onClick` callback reports user interaction to parent
- Reusable and testable because it has no internal state

### State Hoisting Pattern
The pattern demonstrated:
```kotlin
// Parent (Stateful)
var state by remember { mutableStateOf(initial) }
Child(
    value = state,
    onEvent = { state = newValue }
)

// Child (Stateless)
@Composable
fun Child(value: Type, onEvent: (Type) -> Unit) {
    // Use value, call onEvent
}
```

## Best Practices Demonstrated
- State is hoisted to the lowest common ancestor
- Stateless composables are reusable and easier to test
- State delegation with `by` for cleaner code
- Single source of truth for state
- Immutable data flow (state flows down, events flow up)

## Running the Solution
1. Open this project in Android Studio
2. Sync the Gradle files
3. Run the app on an emulator or device
4. Click different mood buttons and see:
   - Visual selection feedback
   - Message updates
   - Only one mood selected at a time

## Learning Points
- `remember` keeps state across recompositions
- `mutableStateOf` creates observable state
- State hoisting separates concerns (logic vs UI)
- Stateless composables lead to better architecture
- Parent decides what to do with events from children
