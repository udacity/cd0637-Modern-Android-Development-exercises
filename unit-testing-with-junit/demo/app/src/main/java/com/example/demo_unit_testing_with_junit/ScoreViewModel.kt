package com.example.demo_unit_testing_with_junit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScoreViewModel : ViewModel() {

    private val _score = MutableStateFlow(0)
    val score = _score.asStateFlow()

    private val _level = MutableStateFlow(1)
    val level = _level.asStateFlow()

    private val _isComboActive = MutableStateFlow(false)
    val isComboActive = _isComboActive.asStateFlow()

    fun addPoints(points: Int) {
        val currentScore = _score.value
        val multiplier = if (_isComboActive.value) 2 else 1
        
        val addedValue = points * multiplier
        val newScore = (currentScore + addedValue).coerceAtLeast(0)
        
        _score.value = newScore
        updateLevel(newScore)
    }

    private fun updateLevel(newScore: Int) {
        // Level up every 50 points
        val newLevel = (newScore / 50) + 1
        if (newLevel != _level.value) {
            _level.value = newLevel
        }
    }

    fun switchCombo() {
        _isComboActive.value = !_isComboActive.value
    }

    fun reset() {
        _score.value = 0
        _level.value = 1
        _isComboActive.value = false
    }
}
