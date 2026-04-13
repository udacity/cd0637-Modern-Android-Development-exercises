package com.example.demo_integrating_compose_with_viewmodel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo_integrating_compose_with_viewmodel.data.MotivationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MotivationViewModel(
    private val repository: MotivationRepository = MotivationRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<MotivationUiState>(MotivationUiState.Loading)
    val uiState: StateFlow<MotivationUiState> = _uiState.asStateFlow()

    private val _moodState = MutableStateFlow(AppMood.CALM)
    val moodState: StateFlow<AppMood> = _moodState.asStateFlow()

    init {
        fetchNewQuote()
    }

    fun fetchNewQuote() {
        viewModelScope.launch {
            _uiState.value = MotivationUiState.Loading
            try {
                val quote = repository.getRandomQuote()
                _uiState.value = MotivationUiState.Success(quote)
            } catch (e: Exception) {
                _uiState.value = MotivationUiState.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun updateMood(mood: AppMood) {
        _moodState.value = mood
    }
}
