package com.udacity.hilt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.hilt.data.UserRepository
import com.udacity.hilt.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

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
