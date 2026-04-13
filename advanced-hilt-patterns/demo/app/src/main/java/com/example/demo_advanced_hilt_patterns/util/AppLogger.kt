package com.example.demo_advanced_hilt_patterns.util

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppLogger @Inject constructor() {
    fun log(message: String) {
        Log.d("HiltDemo", "[AppLogger] $message")
    }
}
