package com.example.demo_integrating_compose_with_viewmodel.ui

import com.example.demo_integrating_compose_with_viewmodel.data.MotivationQuote

sealed class MotivationUiState {
    object Loading : MotivationUiState()
    data class Success(val quote: MotivationQuote) : MotivationUiState()
    data class Error(val message: String) : MotivationUiState()
}

enum class AppMood {
    CALM, ENERGETIC, FOCUSED
}
