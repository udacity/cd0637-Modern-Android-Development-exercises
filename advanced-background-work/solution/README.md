# Exercise 10: Advanced WorkManager - Solution

## Completed: Filter Pipeline
This project contains the **completed** solution for the Advanced WorkManager pipeline.

## Features Implemented
1.  **Work Chaining**:
    *   `cleanupRequest` -> `blurRequest` -> `grayscaleRequest` -> `watermarkRequest`.
    *   Implemented using `.beginWith().then().then().enqueue()`.
2.  **Data Passing**:
    *   Workers pass `KEY_IMAGE_URI` to the next worker in the chain.
    *   `InputMerger` is handled automatically by `OneTimeWorkRequest` default behavior (Overwriting).
3.  **UI Feedback**:
    *   Observes the status of the *last* worker in the chain (`watermarkRequest`).
    *   Displays "Processing Pipeline..." while any part of the chain is running.
    *   Displays the final watermarked image when complete.

## How to Test
1.  Run the app.
2.  Click "Start Filter Pipeline".
3.  Watch the spinner for ~16 seconds (simulated delays).
4.  See the final blurred, grayscaled, and watermarked image!
