# Exercise 17: Advanced Testing Techniques

## Objective
Master advanced integration testing scenarios in Android, focusing on **Room Databases**, **Coroutines (Flows)**, and **Background Work (WorkManager)**.

## Scenario
You are testing the "Offline-First Task Manager" app. This app saves tasks to a local database and has a background worker that simulates syncing validation.

## Instructions

### Task 1: Testing Room (DAO)
**File**: `app/src/androidTest/java/com/udacity/advancedtesting/data/TaskDaoTest.kt`
1.  **Setup**: inside `@Before`, initialize an in-memory database:
    ```kotlin
    database = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        AppDatabase::class.java
    ).allowMainThreadQueries().build()
    ```
2.  **Test**: Implement `insertTaskAndGetInList`.
    - Create a `Task`.
    - Insert it via `dao.insertTask(task)`.
    - Retrieve it via `dao.getTasksStream().first()`.
    - Assert the title matches.

### Task 2: Testing Flows (Turbine)
**File**: `app/src/test/java/com/udacity/advancedtesting/data/TaskFlowTest.kt`
1.  **Test**: Implement `testFlowEmissions` using **Turbine**.
    - Create a simple flow (e.g., `flowOf("A", "B")`).
    - Use `.test { ... }` block.
    - Call `awaitItem()` to verify each emission ("A", then "B").
    - Call `awaitComplete()`.

### Task 3: Testing WorkManager
**File**: `app/src/androidTest/java/com/udacity/advancedtesting/worker/SyncWorkerTest.kt`
1.  **Test**: Implement `testSyncWorker`.
    - Use `TestListenableWorkerBuilder<SyncWorker>(context).build()` to create the worker.
    - Run the worker synchronously using `runBlocking { worker.doWork() }`.
    - Assert that the result is `ListenableWorker.Result.success()`.

## Running Tests
-   **Unit Tests** (`TaskFlowTest`): Run via right-click in Android Studio.
-   **Instrumented Tests** (`TaskDaoTest`, `SyncWorkerTest`): Require an Emulator or Device.
