package com.example.demo_advanced_testing_techniques

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.example.demo_advanced_testing_techniques.data.UserRepository
import com.example.demo_advanced_testing_techniques.worker.SyncWorker
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.kotlin.any

class SyncWorkerTest {
    private val context = mock(Context::class.java)
    private val workerParams = mock(WorkerParameters::class.java)
    private val repository = mock(UserRepository::class.java)

    @Test
    fun testSyncWorkerSuccess() = runBlocking {
        `when`(repository.addUser(any())).thenReturn(Unit)
        
        val worker = SyncWorker(context, workerParams, repository)
            
        val result = worker.doWork()
        assertEquals(ListenableWorker.Result.success(), result)
    }
}
