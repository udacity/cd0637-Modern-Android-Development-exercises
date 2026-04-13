package com.example.demo_advanced_hilt_patterns.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.repository.DataRepository
import com.example.demo_advanced_hilt_patterns.util.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DataRepository,
    private val logger: AppLogger
) : ViewModel() {

    private val _data = MutableStateFlow("Loading...")
    val data: StateFlow<String> = _data

    init {
        logger.log("MainViewModel initialized")
        _data.value = repository.getMessage()
    }
}
