# Exercise 13: Advanced Hilt Setup

## Objective
Learn how to set up Hilt in a multi-module project and correctly annotate classes for Dependency Injection.

## Scenario
You have a `core` module that contains a `LocalDataSource`. You need to make this class available for Hilt injection so it can be used by the `app` module.

## Instructions

### Task 1: Annotate LocalDataSource
**File**: `core/src/main/java/com/udacity/advancedhilt/core/LocalDataSource.kt`

1.  Open `LocalDataSource.kt`.
2.  Annotate the class with `@Singleton` to ensure a single instance is used throughout the app.
3.  Annotate the constructor with `@Inject`.
4.  The constructor requires a `Context`. Use the `@ApplicationContext` qualifier to tell Hilt which Context to provide.

    ```kotlin
    @Singleton
    class LocalDataSource @Inject constructor(
        @ApplicationContext private val context: Context
    ) : DataSource { ... }
    ```

## Running the App
1.  Sync the project.
2.  Run the **app** configuration.
3.  Verify the app launches and displays "Advanced Hilt is Ready!".
