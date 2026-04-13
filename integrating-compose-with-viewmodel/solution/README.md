# Exercise 07 Solution: Quote of the Day - ViewModel Integration

## Solution Overview
This is the complete solution for Exercise 07. The Quote of the Day app demonstrates:
- ViewModel with StateFlow for state management
- Proper MVVM architecture
- UI state modeling with sealed class
- collectAsState() for observing state in Compose
- Repository pattern for data layer
- Coroutine usage in ViewModel

## Key Implementation Details

### UI State (Sealed Class)
Sealed class `QuoteUiState` with three states:
- **Loading**: Initial state and while fetching
- **Success(quote)**: Contains the quote data
- **Error(message)**: Contains error message

Benefits: Type-safe, exhaustive when expressions, clear intent

### ViewModel
- Extends `ViewModel()` from lifecycle library
- Uses `MutableStateFlow` internally (private)
- Exposes `StateFlow` publicly (read-only)
- Uses `viewModelScope` for coroutine management
- Handles loading, success, and error states
- Automatically fetches quote on init

### Repository
- Simulates network delay with `delay()`
- Random quote selection
- 10% simulated failure rate
- Returns suspend function for coroutine usage

### Compose UI
- Uses `viewModel()` to get ViewModel instance
- Uses `collectAsState()` to observe StateFlow
- `when` expression for different states
- Proper separation: UI doesn't know about repository

## Architecture Pattern
```
UI (Compose) → ViewModel → Repository → Data Source (simulated)
     ↑              ↓
     └─ StateFlow ─┘
```

## File Structure
```
app/src/main/java/com/udacity/quote/
├── data/
│   └── QuoteRepository.kt      # Data layer
├── ui/
│   └── QuoteViewModel.kt       # Business logic
└── MainActivity.kt             # UI layer
```

## Running the Solution
1. Open this project in Android Studio
2. Sync the Gradle files
3. Run the app on an emulator or device
4. Observe loading → quote transition
5. Click "Get New Quote" to fetch more
6. Occasionally see error state (10% chance)

## Learning Points
- ViewModel survives configuration changes (rotation)
- StateFlow always has a value, perfect for UI state
- `collectAsState()` automatically subscribes/unsubscribes
- Sealed classes make state handling type-safe
- `viewModelScope` auto-cancels on ViewModel clear
- Repository pattern separates concerns
- Single source of truth (UI state in ViewModel)

## Best Practices Demonstrated
✅ Unidirectional data flow
✅ Immutable state exposure (StateFlow not MutableStateFlow)
✅ Proper coroutine scope usage (viewModelScope)
✅ Error handling in async operations
✅ Loading states for better UX
✅ Separation of concerns (MVVM)
