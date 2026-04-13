# Exercise 15: Mocking & Test Doubles

In this exercise, you will learn how to write unit tests for a business logic class by isolating it from its dependencies using **Fakes** and **Mocks**.


## Objective
Learn to create **Test Doubles (Fakes)** and use **Mocking libraries (MockK)** to test business logic in isolation.

## Scenario
You are testing a `UserSyncManager` class.
- It fetches users from a `UserApiService` (Network).
- It saves users to a `UserDatabase` (Storage).
- You need to test the logic WITHOUT using a real server or real database.

## Instructions

### Task 1: Create a Fake
**File**: `com/udacity/mocking/data/FakeUserDatabase.kt`
- Implement `FakeUserDatabase` using a simple `MutableList<User>` in memory.
- `saveUsers()` should clear the list and add new items.
- `getAllUsers()` should return a copy of the list.

### Task 2: Write Unit Tests with MockK
**File**: `src/test/java/com/udacity/mocking/UserSyncManagerTest.kt`

#### Test: `syncSuccess_savesUsersToDatabase`
1.  **Stub**: Use `every { mockApi.getUsers() } returns listOf(...)` to verify what happens when the API works.
2.  **Act**: Call `syncManager.syncUsers()`.
3.  **Assert**: Check `fakeDatabase.getAllUsers()` to see if data was "saved".

#### Test: `syncFailure_doesNotSaveUsers`
1.  **Stub**: Use `every { mockApi.getUsers() } throws IOException()` to simulate a crash.
2.  **Act**: Call `syncManager.syncUsers()`.
3.  **Assert**: Verify `fakeDatabase` remains empty.

## Running Tests
Right-click `UserSyncManagerTest` and select **Run**.
