package com.example.demo_advanced_hilt_patterns.util

import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ScreenNavigator @Inject constructor() {
    val id = (0..1000).random()
    fun navigate() = "Navigating from Screen (ID: $id)"
}
