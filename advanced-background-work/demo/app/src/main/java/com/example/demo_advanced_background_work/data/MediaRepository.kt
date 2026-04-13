package com.example.demo_advanced_background_work.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class MediaRepository @Inject constructor() {
    private val _logs = MutableStateFlow<List<String>>(emptyList())
    val logs: StateFlow<List<String>> = _logs

    fun addLog(message: String) {
        _logs.value = _logs.value + "${System.currentTimeMillis() % 10000}: $message"
    }
}
