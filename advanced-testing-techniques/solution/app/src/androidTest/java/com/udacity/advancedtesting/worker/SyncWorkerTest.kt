package com.udacity.advancedtesting.worker

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SyncWorkerTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Test
    fun testSyncWorker() {
        val worker = TestListenableWorkerBuilder<SyncWorker>(context).build()
        
        runBlocking {
            val result = worker.doWork()
            assertEquals(ListenableWorker.Result.success(), result)
        }
    }
}
