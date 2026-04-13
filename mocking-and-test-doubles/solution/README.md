# Exercise 15: Mocking and Test Doubles (Solution)

This is the reference solution for Exercise 15. It demonstrates how to use **Mocks** and **Fakes** to test a `UserSyncManager` in isolation.

## Components Tested
-   **`UserSyncManager`**: The class under test. It fetches users from an API and saves them to a database.
-   **`UserApiService`**: A dependency that we **Mock** (simulate network calls).
-   **`UserDatabase`**: A dependency that we **Fake** (simulate database storage).

## Key Implementation Details

### 1. FakeUserDatabase (The Fake)
Instead of using a real Room database (which would be slow and require Android Context), we created a `FakeUserDatabase`.
-   It uses a simple `MutableList` to store users in memory.
-   It implements the same interface as the real database, so the `UserSyncManager` can't tell the difference.

### 2. Mocking with MockK (The Mock)
We use **MockK** to simulate the behavior of the `UserApiService`.
-   **`mockk<UserApiService>()`**: Creates the mock object.
-   **`every { api.getUsers() } returns ...`**: Defines what the mock should do when called. We test both success (returns a list) and failure (throws exception) scenarios.
-   **`verify`**: Checks if the `UserSyncManager` actually called the API methods as expected.

### 3. Verifying Results
-   **Success Case**: We verify that users were fetched from the mock API and saved into our Fake database.
-   **Error Case**: We verify that if the API fails, the database remains empty and the appropriate error result is returned.

## How to Run Tests
1.  Open `src/test/java/com/udacity/mocking/UserSyncManagerTest.kt`.
2.  Click the **Green Play Icons** next to the class name or individual test methods.
