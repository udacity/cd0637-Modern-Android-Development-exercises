# Exercise 09: WorkManager (Solution)

This is the solution code for the "Blur-O-Matic" exercise.

## Features Implemented
- **BlurWorker**: Performs simulated image processing in the background using `Thread.sleep` (simulating a heavy computation).
- **OneTimeWorkRequest**: Triggers a single execution of the blur task.
- **UI Observation**: Uses `workManager.getWorkInfoByIdLiveData` and `observeAsState` to show a progress spinner while the work is running.

## How to Test
1. Build and Run the app.
2. Click "Start Blur".
3. Observe the loading spinner for ~3 seconds.
4. "Work Finished" message appears when done.
