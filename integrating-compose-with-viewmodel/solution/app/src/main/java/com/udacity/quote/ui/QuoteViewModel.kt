package com.udacity.quote.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.quote.data.QuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * UI State for the Quote screen.
 * 
 * Sealed class provides type-safe state representation.
 * Each state represents a different UI condition.
 */
sealed class QuoteUiState {
    // Loading state - shown while fetching data
    object Loading : QuoteUiState()
    
    // Success state - contains the quote data
    data class Success(val quote: String) : QuoteUiState()
    
    // Error state - contains error message
    data class Error(val message: String) : QuoteUiState()
}

/**
 * QuoteViewModel - Manages UI state for the Quote screen.
 * 
 * This ViewModel:
 * - Holds UI state in StateFlow
 * - Fetches quotes from repository
 * - Handles loading and error states
 * - Survives configuration changes
 */
class QuoteViewModel(
    private val repository: QuoteRepository = QuoteRepository()
) : ViewModel() {
    
    // Private mutable state - only ViewModel can modify
    private val _uiState = MutableStateFlow<QuoteUiState>(QuoteUiState.Loading)
    
    // Public immutable state - UI can only observe
    val uiState: StateFlow<QuoteUiState> = _uiState
    
    // Fetch initial quote when ViewModel is created
    init {
        fetchQuote()
    }
    
    /**
     * Fetches a new quote from the repository.
     * Updates UI state based on the result.
     * 
     * This function:
     * 1. Sets state to Loading
     * 2. Calls repository (suspend function)
     * 3. Updates state to Success or Error
     */
    fun fetchQuote() {
        // Launch coroutine in viewModelScope
        // viewModelScope automatically cancels when ViewModel is cleared
        viewModelScope.launch {
            // Set to loading state
            _uiState.value = QuoteUiState.Loading
            
            try {
                // Fetch quote from repository (suspend function)
                val quote = repository.getRandomQuote()
                
                // Update state to success with quote
                _uiState.value = QuoteUiState.Success(quote)
            } catch (e: Exception) {
                // Update state to error with message
                _uiState.value = QuoteUiState.Error(
                    e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}
