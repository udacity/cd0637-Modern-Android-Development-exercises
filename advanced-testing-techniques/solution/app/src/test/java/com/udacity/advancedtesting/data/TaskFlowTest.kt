package com.udacity.advancedtesting.data

import app.cash.turbine.test
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskFlowTest {

    @Test
    fun testFlowEmissions() = runTest {
        val flow = flowOf("A", "B", "C")
        
        flow.test {
            assertEquals("A", awaitItem())
            assertEquals("B", awaitItem())
            assertEquals("C", awaitItem())
            awaitComplete()
        }
    }
}
