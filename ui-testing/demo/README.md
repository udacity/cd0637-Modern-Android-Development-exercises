# Demo: UI Testing with Jetpack Compose

## Overview
This demo project, `Demo-UI-Testing`, is an educational guide to writing automated UI tests for Jetpack Compose applications. It covers a full end-to-end authentication flow, demonstrating how to verify UI state, handle user interactions, and manage asynchronous operations.

## Key Features
- **Member Login Flow**: A standard login screen with username/password validation.
- **Asynchronous States**: Simulated network latency with loading indicators.
- **Error Handling**: Visual feedback for failed authentication attempts.
- **Navigation Flow**: Seamless transition from Login to a Dashboard upon success.

## Testing Coverage

### 1. Verification of Data
- Ensuring labels and placeholders are correctly displayed using `onNodeWithText`.
- Verifying the presence of critical UI elements like buttons and input fields.

### 2. User Interactions
- Simulating text input into `TextField` components.
- Performing click actions on `Button` components.

### 3. Navigation Flow
- Verifying that successful login triggers a transition to the `DashboardScreen`.
- Testing backstack or screen persistence where applicable.

### 4. Asynchronous Handling
- Using `waitUntil` patterns to wait for the authentication process to complete.
- Verifying that loading spinners appear and disappear at the correct times.

### 5. Error States
- Asserting that error messages are displayed when invalid credentials are provided.

## Tools & Libraries
- **Compose Testing Library**: `androidx.compose.ui:ui-test-junit4` for finding and interacting with nodes.
- **Hilt Testing**: For dependency injection in instrumented tests.
- **JUnit 4**: The standard runner for Android instrumented tests.

## Running the Tests
1. Open the project in Android Studio.
2. Navigate to `app/src/androidTest/java/com/example/demo_ui_testing/LoginScreenTest.kt`.
3. Right-click the class and select **Run 'LoginScreenTest'**.
4. The test will run on your connected device or emulator.
