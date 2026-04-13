# Exercise 17: Advanced Testing Techniques (Solution)

This is the reference solution for Exercise 17. It demonstrates advanced testing scenarios including **Integration Testing**, **Room Database Testing**, **WorkManager Testing**, and **Flow Testing with Turbine**.

## Key Concepts Implemented

### 1. In-Memory Room Database
For database tests, we don't want to persist data to the device's actual storage. We use `Room.inMemoryDatabaseBuilder`.
-   This creates a temporary database that lives only as long as the test process.
-   It ensures every test starts with a clean slate.

### 2. Testing Flows with Turbine
`Flow` is a stream of data. Testing it with standard JUnit assertions is difficult. We use **Turbine** to consume the flow events.
```kotlin
repository.tasks.test {
    val item = awaitItem() // Waits for the first emission
    assertEquals(expectedItem, item)
}
```

### 3. WorkManager Testing
Testing background workers requires special tools because they run asynchronously.
-   We use `TestListenableWorkerBuilder` to create an instance of our `SyncWorker`.
-   We call `doWork()` directly (synchronously) to verify its logic without waiting for the actual WorkManager scheduler constraints.

## How to Run Tests
1.  **Unit Tests (Robolectric/Local)**: Open `src/test/java/...` and run the tests.
2.  **Instrumented Tests (Room/WorkManager)**: Open `src/androidTest/java/...` and run the tests on an emulator/device.
