# Exercise 16: UI Testing with Compose (Solution)

This is the reference solution for Exercise 16. It demonstrates how to write **Instrumented UI Tests** for a Jetpack Compose application.

## Scenario
The app has a **Login Screen**.
-   **Valid Credentials**: user / pass -> Navigates to Dashboard ("Welcome").
-   **Invalid Credentials**: Anything else -> Shows "Invalid Credentials" error.

## Key Implementation Details

### 1. Test Rules
We use two rules to set up the environment:
1.  **`HiltAndroidRule`**: Sets up dependency injection for the test.
2.  **`createAndroidComposeRule`**: Sets up the Compose content and provides the `onNode` finders.

### 2. Finding UI Elements
We use semantic matchers to find elements on screen:
-   `onNodeWithTag("username_input")`: Finds the text field by its test tag.
-   `onNodeWithText("Login")`: Finds the button by its text.

### 3. Performing Actions
-   `performTextInput("...")`: Types text into a field.
-   `performClick()`: Clicks a button.

### 4. Assertions
-   `assertIsDisplayed()`: Verifies that a UI element is visible to the user.
-   We verify the **Success Path** by checking for the "Welcome" message.
-   We verify the **Error Path** by checking for the "Invalid Credentials" message.

## How to Run Tests
1.  Make sure you have an Android Emulator running or a device connected.
2.  Open `src/androidTest/java/com/udacity/uitesting/LoginScreenTest.kt`.
3.  Click the **Green Play Icons** next to the class name or test methods.
