# Exercise 13: Advanced Hilt Patterns (Solution)

This is the reference solution for Exercise 13. It demonstrates how to use advanced Hilt features like **Qualifiers**, **Scopes**, and **Multi-Module Injection**.

## Application Overview
The app displays a simple list of data items. The data comes from two sources:
1.  **Local Source**: An in-memory list (Simulated Database).
2.  **Remote Source**: A simulated network fetch.

The app uses a **Multi-Module Architecture**:
-   **:core**: Contains the `DataSource` interface.
-   **:app**: Contains the implementation (`DiskDataSource`, `NetworkDataSource`) and the UI.

## Key Concepts Implemented

### 1. Qualifiers
Since we have two implementations of `DataSource` (`DiskDataSource` and `NetworkDataSource`), we cannot just inject `DataSource`. Hilt wouldn't know which one to provide.

We used **Qualifiers** to solve this:
-   `@LocalSource`: Annotates the `DiskDataSource`.
-   `@RemoteSource`: Annotates the `NetworkDataSource`.

**Code Location**: `com.udacity.advancedhilt.di.DataSourceModule`

### 2. Scoping & Lifecycle
We applied different scopes to demonstrate lifecycle management:
-   **`@Singleton` (Local Source)**: The `DiskDataSource` is a Singleton. Its data persists as long as the app is alive. Rotating the screen does *not* reset its state.
-   **`@ActivityScoped` (Remote Source)**: The `NetworkDataSource` is scoped to the Activity. Rotating the screen (which destroys and recreates the Activity) creates a *new* instance of this source.

### 3. Hilt Testing
This solution includes an Instrumented Test that uses Hilt to inject dependencies in a test environment.

-   **`HiltTestRunner`**: A custom runner required for Hilt tests.
-   **`AppTest`**: A simple test that verifies the app launches.

## How to Verify Scoping (Rotation Test)
1.  Run the app on an emulator or device.
2.  Observe the items in the list.
3.  **Rotate the device**.
4.  Notice that the "Local" data remains consistent (same instance), while the "Remote" data might be re-fetched or re-instantiated depending on how the ViewModel handles it (in this specific exercise, we focus on the *injection* scope).

## Running Tests
To run the Hilt instrumented tests:
1.  Open the **Project** pane.
2.  Right-click on `com.udacity.advancedhilt (androidTest)`.
3.  Select **Run 'Tests in 'com.udacity....'**.
