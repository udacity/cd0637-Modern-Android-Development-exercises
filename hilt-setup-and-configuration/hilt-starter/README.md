# Exercise 12: Hilt Setup and Configuration

## Learning Objective
Learn to set up **Dependency Injection** in Android using **Hilt**.
You will refactor an app that uses manual dependency injection to use Hilt.

## Instructions

### Step 1: Add Dependencies
Open `app/build.gradle.kts`.
1.  Add the Hilt plugin: `id("com.google.dagger.hilt.android")`.
2.  Add dependencies:
    *   `implementation("com.google.dagger:hilt-android:2.48")`
    *   `kapt("com.google.dagger:hilt-android-compiler:2.48")`
3.  Sync Gradle.

### Step 2: Hilt Application Class
1.  Create a new class `HiltApplication` extending `Application`.
2.  Annotate it with `@HiltAndroidApp`.
3.  Register it in `AndroidManifest.xml` (add `android:name=".HiltApplication"` to the `<application>` tag).

### Step 3: Create a Module
1.  Create a package `di`.
2.  Create an object `DiModule`.
3.  Annotate with `@Module` and `@InstallIn(SingletonComponent::class)`.
4.  **Provide the Data Source String**:
    *   Create a function `provideDataSource()` annotated with `@Provides` and `@Singleton`.
    *   Return a string (e.g., "Provided via Hilt").
5.  **Provide the Repository**:
    *   Create a function `provideUserRepository(dataSource: String)` annotated with `@Provides` and `@Singleton`.
    *   Return a new `UserRepositoryImpl(dataSource)`.

### Step 4: Refactor ViewModel
Open `MainViewModel.kt`.
1.  Annotate the class with `@HiltViewModel`.
2.  Annotate the constructor with `@Inject`.
3.  **Delete** the `MainViewModelFactory` class (Hilt handles this now!).

### Step 5: Refactor Activity
Open `MainActivity.kt`.
1.  Annotate `MainActivity` with `@AndroidEntryPoint`.
2.  Change the `viewModel` initialization to:
    `private val viewModel: MainViewModel by viewModels()`
3.  Remove the manual factory instantiation.

## Run the App
Verify that the app still works and loads the user data, but now powered by Hilt!
