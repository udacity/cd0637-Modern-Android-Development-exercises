# Exercise 07: Quote of the Day - ViewModel Integration

## Learning Objective
Connect Compose UI to ViewModel and observe state changes using modern Android architecture

## Duration
25-30 minutes

## Overview
In this exercise, you'll build a Quote of the Day app that demonstrates proper MVVM architecture in Compose. You'll learn how to create a ViewModel, manage UI state with StateFlow, and observe that state in your Compose UI using `collectAsState()`.

## What You'll Build
A Quote of the Day app featuring:
- ViewModel with StateFlow for state management
- UI state (Loading, Success, Error)
- Repository for data fetching (simulated)
- Compose UI observing ViewModel state
- Button to fetch new quotes
- Proper separation of concerns

## Skills Practiced
- Creating ViewModels for Compose
- StateFlow for state management
- collectAsState() for observing state
- UI state modeling (sealed class)
- Event handling through ViewModel
- Repository pattern basics
- MVVM architecture in Compose

## Instructions

### Step 1: Review the Project Structure
The project has three main files:
- `QuoteRepository.kt` - Data layer (already provided)
- `QuoteViewModel.kt` - Business logic layer (you'll implement)
- `MainActivity.kt` - UI layer (you'll implement)

### Step 2: Define UI State
In `QuoteViewModel.kt`:
1. Create a sealed class `QuoteUiState` with three states:
   - `Loading` - while fetching
   - `Success(quote: String)` - with quote data
   - `Error(message: String)` - with error message

### Step 3: Create the ViewModel
1. Create `QuoteViewModel` extending `ViewModel()`
2. Inject `QuoteRepository` in constructor
3. Create private `MutableStateFlow<QuoteUiState>` initialized to `Loading`
4. Expose public `StateFlow<QuoteUiState>` (read-only)
5. Create `fetchQuote()` function that:
   - Sets state to Loading
   - Calls repository
   - Updates state to Success or Error

### Step 4: Initialize ViewModel
In `MainActivity.kt`:
1. Use `viewModel()` to create/get the ViewModel instance
2. Use `collectAsState()` to observe the state
3. Remember: Add ViewModel dependency to build.gradle

### Step 5: Build the UI
Create UI that responds to different states:
1. **Loading**: Show loading indicator
2. **Success**: Display the quote
3. **Error**: Show error message
4. Add "Get New Quote" button that calls `viewModel.fetchQuote()`

### Step 6: Test the App
1. Run the app - should show loading then a quote
2. Click "Get New Quote" - should show loading then new quote
3. Observe state changes in action

## Expected Result
When complete, you should see:
- App starts with loading state
- Quote appears after brief delay
- "Get New Quote" button
- Clicking button shows loading then new quote
- Smooth state transitions
- Proper MVVM architecture

## Tips
- ViewModel survives configuration changes (rotation)
- StateFlow is like LiveData but for Kotlin coroutines
- `collectAsState()` converts Flow to Compose State
- Use `viewModelScope` for coroutines in ViewModel
- Sealed classes are perfect for UI states
- Repository pattern separates data logic from UI logic
- Always expose immutable state from ViewModel (StateFlow, not MutableStateFlow)

## Key Concepts
**ViewModel**: Survives configuration changes, holds UI state
**StateFlow**: Hot flow that always has a value, perfect for UI state
**collectAsState()**: Collects Flow values as Compose State
**UI State**: Single source of truth for what UI should display
**Repository**: Handles data operations, could be network/database

## Resources
- [ViewModel in Compose](https://developer.android.com/jetpack/compose/libraries#viewmodel)
- [State and Jetpack Compose](https://developer.android.com/jetpack/compose/state)
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)

## Need Help?
If you get stuck:
1. ViewModel needs `androidx.lifecycle:lifecycle-viewmodel-compose` dependency
2. Use `by viewModel()` to get ViewModel instance
3. Use `val state by viewModel.uiState.collectAsState()`
4. Use `when` expression to handle different states
5. Call repository methods inside `viewModelScope.launch { }`

Good luck! 🚀
