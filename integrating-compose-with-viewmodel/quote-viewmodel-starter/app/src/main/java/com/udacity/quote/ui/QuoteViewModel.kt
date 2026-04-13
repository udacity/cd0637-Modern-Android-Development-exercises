package com.udacity.quote.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.quote.data.QuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// TODO: Step 1 - Define UI State
// Create a sealed class QuoteUiState with three states:
// - object Loading : QuoteUiState()
// - data class Success(val quote: String) : QuoteUiState()
// - data class Error(val message: String) : QuoteUiState()

/**
 * QuoteViewModel - Manages UI state for the Quote screen.
 * 
 * TODO: Complete this ViewModel implementation
 */
class QuoteViewModel(
    private val repository: QuoteRepository = QuoteRepository()
) : ViewModel() {
    
    // TODO: Step 2 - Create StateFlow for UI state
    // Create a private MutableStateFlow<QuoteUiState> initialized to Loading
    // val _uiState = MutableStateFlow<QuoteUiState>(QuoteUiState.Loading)
    
    // TODO: Step 3 - Expose public read-only StateFlow
    // Create a public StateFlow that exposes _uiState
    // val uiState: StateFlow<QuoteUiState> = _uiState
    
    // Fetch initial quote when ViewModel is created
    init {
        fetchQuote()
    }
    
    // TODO: Step 4 - Implement fetchQuote function
    /**
     * Fetches a new quote from the repository.
     * Updates UI state based on the result.
     */
    fun fetchQuote() {
        // TODO: Launch a coroutine in viewModelScope
        // viewModelScope.launch {
        //     // Set state to Loading
        //     _uiState.value = QuoteUiState.Loading
        //     
        //     try {
        //         // Fetch quote from repository
        //         val quote = repository.getRandomQuote()
        //         // Set state to Success with the quote
        //         _uiState.value = QuoteUiState.Success(quote)
        //     } catch (e: Exception) {
        //         // Set state to Error with error message
        //         _uiState.value = QuoteUiState.Error(e.message ?: "Unknown error")
        //     }
        // }
    }
}
