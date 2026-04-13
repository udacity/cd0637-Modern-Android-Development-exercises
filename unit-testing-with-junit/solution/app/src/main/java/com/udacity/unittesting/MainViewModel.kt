package com.udacity.unittesting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> = _counter

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    fun increment() {
        val current = _counter.value ?: 0
        _counter.value = current + 1
    }

    fun fetchData() {
        viewModelScope.launch {
            _data.value = "Loading..."
            delay(1000) // Simulate network delay
            _data.value = "Success!"
        }
    }
}
