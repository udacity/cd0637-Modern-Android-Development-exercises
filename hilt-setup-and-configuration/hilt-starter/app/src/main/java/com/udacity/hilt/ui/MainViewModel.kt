package com.udacity.hilt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.udacity.hilt.data.UserRepository
import com.udacity.hilt.domain.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _user.value = repository.getUser()
            } catch (e: Exception) {
                // handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}

// TODO: Refactor this to use @HiltViewModel and remove the Factory
class MainViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
