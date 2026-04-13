# Exercise 14: Unit Testing ViewModels

## Objective
Learn how to test Android ViewModels, LiveData, and Coroutines using JUnit 4 and `kotlinx-coroutines-test`.

## Scenario
You have a `MainViewModel` with:
1.  A simple counter (`increment()`).
2.  An async operation (`fetchData()`) that has a 1 second delay.

## Instructions
**File**: `src/test/java/com/udacity/unittesting/MainViewModelTest.kt`

### Task 1: Setup Rules
1.  Add `InstantTaskExecutorRule` to the test class. This forces LiveData to update synchronously.
    ```kotlin
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    ```

### Task 2: Test Synchronous Logic
Implement `increment_increasesCounter()`:
1.  Call `viewModel.increment()`.
2.  Assert `viewModel.counter.value` equals 1.

### Task 3: Test Coroutines
Implement `fetchData_updatesDataAfterDelay()` using `runTest`:
1.  Call `viewModel.fetchData()`.
2.  Use `testScheduler.runCurrent()` to execute pending coroutines (the initial "Loading" emission).
3.  Assert value is "Loading...".
4.  Use `advanceUntilIdle()` to skip past the 1000ms delay.
5.  Assert value is "Success!".

## Running Tests
Right-click `MainViewModelTest` and select **Run**.
