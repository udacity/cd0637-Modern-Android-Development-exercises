# Exercise 10: Advanced WorkManager - Filter Pipeline

## Learning Objective
Master advanced WorkManager concepts: Chaining multiple workers, passing data between them, and handling complex workflows.

## Overview
In this exercise, you will upgrade "Blur-O-Matic" into a full "Filter Pipeline".
Instead of just blurring, we want to:
1.  **Cleanup**: Delete old temporary files to save space.
2.  **Blur**: Blur the image (already done!).
3.  **Grayscale**: Turn the blurred image black & white.
4.  **Watermark**: Add a "UDACITY" watermark to the image.

## Instructions

### Step 1: Review the New Workers
We have added `CleanupWorker.kt`, `GrayscaleWorker.kt`, and `WatermarkWorker.kt` for you.
Take a look at them. Notice how they read `Constants.KEY_IMAGE_URI` from `inputData` and save their result to `Constants.KEY_IMAGE_URI` in `outputData`. This is crucial for chaining!

### Step 2: Create the Chain
Open `MainActivity.kt`.
Find the "Start Filter Pipeline" button.

1.  Create `OneTimeWorkRequest` objects for all four workers:
    *   `CleanupWorker`
    *   `BlurWorker`
    *   `GrayscaleWorker`
    *   `WatermarkWorker`

2.  Chain them together using `beginWith()` and `then()`:
    ```kotlin
    workManager
        .beginWith(cleanupRequest)
        .then(blurRequest)
        .then(...) // grayscale
        .then(...) // watermark
        .enqueue()
    ```

### Step 3: Observe the Final Result
To show the *final* image, we need to observe the *last* worker in the chain.
1.  Set `workId = watermarkRequest.id`.

The UI is already set up to observe this ID and display the image found in `Constants.KEY_IMAGE_URI`.

## Run the App
1.  Click "Start Filter Pipeline".
2.  Wait... (it might take ~12 seconds now due to multiple sleeps/processing).
3.  **Success!** You should see a **Blurred, Black & White, Watermarked** version of the mountain!
