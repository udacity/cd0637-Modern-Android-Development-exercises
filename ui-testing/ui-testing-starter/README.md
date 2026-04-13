# Exercise 16: UI Testing with Jetpack Compose

## Objective
Learn how to write automated UI tests for Jetpack Compose screens using the Compose Testing Library.

## Scenario
You have a simple **Login Screen** that accepts a username and password.
- **Valid Credentials**: Username="user", Password="pass".
- **Action**: Clicking "Login" with valid credentials navigates to the Dashboard.
- **Error**: Clicking "Login" with invalid credentials shows an error message.

## Instructions
1.  Open the file: `app/src/androidTest/java/com/udacity/uitesting/LoginScreenTest.kt`.
2.  Locate the `LoginScreenTest` class. It is already set up with `HiltAndroidRule` and `createAndroidComposeRule`.

### Task 1: Test Login Success
Implement the `loginSuccess_navigatesToDashboard()` test:
-   Use `composeTestRule.onNodeWithTag("username_field")` to find the input field.
-   Perform text input using `.performTextInput("user")`.
-   Do the same for the password field ("pass").
-   Find the Login button ("login_button") and call `.performClick()`.
-   **Verify**: Assert that the `welcome_message` node is displayed and contains the text "Welcome, user!".

### Task 2: Test Login Failure
Implement the `loginFailure_showsError()` test:
-   Enter invalid credentials (e.g., "wrong", "wrong").
-   Click the Login button.
-   **Verify**: Assert that the `error_message` node is displayed and contains "Invalid Credentials".

## Running Tests
-   Right-click on the `LoginScreenTest` class or the individual test methods in Android Studio (green gutter icon) and select **Run**.
-   Ensure you have an Emulator running or a Device connected.
