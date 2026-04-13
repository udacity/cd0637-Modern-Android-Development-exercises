package com.example.demo_hilt_setup_and_configuration.ui

import androidx.lifecycle.ViewModel
import com.example.demo_hilt_setup_and_configuration.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    userRepository: UserRepository
) : ViewModel() {
    val user = userRepository.getUser()
}
