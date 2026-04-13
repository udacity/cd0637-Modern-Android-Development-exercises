# Exercise 14: Unit Testing with JUnit (Solution)

This is the reference solution for Exercise 14. It demonstrates how to write unit tests for Android ViewModels using **JUnit 4**, **MockK**, and **Coroutines Test**.

## Concepts Covered
-   **JUnit 4**: Setup and Teardown (`@Before`, `@After`), Assertions.
-   **MockK**: Mocking dependencies.
-   **LiveData Testing**: Using `InstantTaskExecutorRule`.
-   **Coroutines Testing**: Controlling execution time with `runTest` and `StandardTestDispatcher`.

## Key Implementation Details

### 1. InstantTaskExecutorRule
`LiveData` uses the Android Main Looper to set values. In a local unit test, the Main Looper doesn't exist. We use `InstantTaskExecutorRule` to swap the background executor with a synchronous one, allowing LiveData to update immediately.

```kotlin
@get:Rule
val instantTaskExecutorRule = InstantTaskExecutorRule()
```

### 2. Coroutines Testing
We use `StandardTestDispatcher` to control the execution of coroutines. This allows us to "fast-forward" time for tests that involve `delay()`.

-   **`Dispatchers.setMain`**: Replaces the Main dispatcher with our test dispatcher.
-   **`runTest`**: The scope for running coroutine tests.
-   **`advanceUntilIdle()`**: executes all pending coroutines, effectively skipping any delays.

### 3. Testing Logic
-   **`test_increment`**: Verifies that calling `increment()` updates the counter LiveData.
-   **`test_fetchData`**: Verifies that `fetchData()` (which has a delay) correctly updates the state after the delay.

## How to Run Tests
1.  Open `src/test/java/com/udacity/unittesting/MainViewModelTest.kt`.
2.  Click the **Green Play Icons** next to the class name (to run all tests) or individual test methods.
