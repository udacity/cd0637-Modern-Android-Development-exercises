package com.example.demo_advanced_background_work.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.work.*
import com.example.demo_advanced_background_work.data.MediaRepository
import com.example.demo_advanced_background_work.workers.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MediaHubViewModel @Inject constructor(
    private val repository: MediaRepository,
    private val workManager: WorkManager
) : ViewModel() {

    val logs: StateFlow<List<String>> = repository.logs
    
    // Track the current work chain
    private var currentChainId: UUID? = null

    fun startProcessing() {
        repository.addLog("Initializing Media Pipeline...")

        // 1. Download Stage
        val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>().build()
        currentChainId = downloadRequest.id

        // 2. Parallel Filter Stage
        val blurRequest = OneTimeWorkRequestBuilder<BlurFilterWorker>().build()
        val grayscaleRequest = OneTimeWorkRequestBuilder<GrayscaleFilterWorker>().build()

        // 3. Upload Stage
        val uploadRequest = OneTimeWorkRequestBuilder<UploadWorker>().build()

        // Chain execution: Download -> (Blur & Grayscale) -> Upload
        workManager.beginWith(downloadRequest)
            .then(listOf(blurRequest, grayscaleRequest))
            .then(uploadRequest)
            .enqueue()
    }

    fun cancelWork() {
        workManager.cancelAllWork()
        repository.addLog("Work Cancelled by User. 🛑")
    }

    fun getWorkStatus() = workManager.getWorkInfosByTagLiveData("media_work").asFlow() 
    // Simplified for demo; usually you'd track by ID or Tag
}
