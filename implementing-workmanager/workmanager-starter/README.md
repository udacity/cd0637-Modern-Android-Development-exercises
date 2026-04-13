# Exercise 09: WorkManager - Blur-O-Matic

## Learning Objective
Schedule and manage background work using WorkManager. You will learn how to offload heavy tasks (like image processing) to a background worker so your app remains responsive.

## Duration
20-30 minutes

## Overview
In this exercise, you will complete the "Blur-O-Matic" app.
*   **The Goal**: Take a picture of a mountain and blur it.
*   **The Problem**: Blurring an image is "expensive" (takes time). If we do it on the main thread, the app freezes.
*   **The Solution**: Use `WorkManager` to run this task in the background.

We have provided a helper class `WorkerUtils.kt` that handles the actual image manipulation code for you. Your job is to wire up the **Worker** and the **WorkRequest**.

## Instructions

### Step 1: Implement the Worker
Open `app/src/main/java/com/udacity/workmanager/worker/BlurWorker.kt`.
This class extends `Worker`. It is where the background code lives.

1.  **Decode the Image**: In `doWork()`, used the provided code to load `R.drawable.mountain` into a Bitmap.
2.  **Blur the Image**: Use `WorkerUtils.blurBitmap(picture, applicationContext)` to create a blurred version.
3.  **Save the File**: Use `WorkerUtils.writeBitmapToFile(...)` to save it to disk. This returns a `Uri`.
4.  **Return Output**: Create a `Data` object containing the `Uri` and return `Result.success(outputData)`.

### Step 2: Create the Work Request
Open `MainActivity.kt`.
Find the `Button` onClick listener.

1.  Create a `OneTimeWorkRequest` for your `BlurWorker` class.
    ```kotlin
    val blurRequest = OneTimeWorkRequestBuilder<BlurWorker>().build()
    ```

### Step 3: Enqueue the Work
Still in the `onClick` listener:

1.  Get the `WorkManager` instance (passed in or retrieved via `WorkManager.getInstance(context)`).
2.  Call `enqueue(blurRequest)` to start the work.

### Step 4: Observer Progress (UI Update)
The UI needs to know when the work starts and finishes.
1.  Assign `blurRequest.id` to the `workId` state variable: `workId = blurRequest.id`.
    *   *Note*: The provided UI code already watches `workId` using `workManager.getWorkInfoByIdLiveData(...)`. When you update `workId`, the UI will automatically react!

## Run the App
1.  You should see the crisp mountain image.
2.  Click "Start Blur".
3.  You should see a loading spinner for ~10 seconds (we added a delay to simulate a large image).
4.  **Success!** The image should update to show the blurred version, and the text "Blur Complete!" should appear.
