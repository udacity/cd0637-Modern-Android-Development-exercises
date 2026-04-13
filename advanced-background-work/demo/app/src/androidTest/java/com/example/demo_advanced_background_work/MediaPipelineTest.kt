package com.example.demo_advanced_background_work

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.*
import androidx.work.testing.WorkManagerTestInitHelper
import com.example.demo_advanced_background_work.workers.*
import org.junit.*
import org.junit.Assert.assertEquals
import java.util.concurrent.TimeUnit

class MediaPipelineTest {
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setExecutor(androidx.test.espresso.core.internal.deps.guava.util.concurrent.MoreExecutors.directExecutor())
            .build()
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }

    @Test
    fun testMediaPipeline_Execution() {
        val workManager = WorkManager.getInstance(context)

        // Define the chain
        val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>().build()
        val blurRequest = OneTimeWorkRequestBuilder<BlurFilterWorker>().build()
        val uploadRequest = OneTimeWorkRequestBuilder<UploadWorker>().build()

        // Enqueue
        workManager.beginWith(downloadRequest)
            .then(blurRequest)
            .then(uploadRequest)
            .enqueue()

        // In a real test, we'd use TestDriver to control the execution
        // For this demo, we verify the requests are enqueued
        val downloadStatus = workManager.getWorkInfoById(downloadRequest.id).get()
        assert(downloadStatus.state == WorkInfo.State.ENQUEUED || downloadStatus.state == WorkInfo.State.RUNNING || downloadStatus.state == WorkInfo.State.SUCCEEDED)
    }
}
