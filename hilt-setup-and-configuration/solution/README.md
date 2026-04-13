# Exercise 12: Hilt Setup - Solution

## Completed: Hilt Demo
This project contains the **completed** solution for Hilt Dependency Injection.

## Features Implemented
1.  **Hilt Setup**:
    *   `build.gradle.kts` includes Hilt plugin and dependencies.
    *   `HiltApplication` class created and annotated with `@HiltAndroidApp`.
    *   Registered in Manifest.
2.  **Dependency Injection**:
    *   `DiModule` uses `@Provides` to supply a String dependency and the `UserRepository`.
    *   `UserRepositoryImpl` uses `@Inject constructor` to receive the String.
    *   `MainViewModel` accepts `UserRepository` via `@Inject`.
    *   `MainActivity` uses `@AndroidEntryPoint` to inject the ViewModel.

## How to Verify
1.  Run the app.
2.  Wait 1 second for the "Network Delay".
3.  See "Hilt User" details displayed on the screen.
