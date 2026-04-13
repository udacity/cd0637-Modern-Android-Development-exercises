package com.udacity.quote.data

import kotlinx.coroutines.delay

/**
 * QuoteRepository - Handles data operations for quotes.
 * 
 * In a real app, this would fetch from a network API or database.
 * For this exercise, we're simulating with hardcoded quotes and delays.
 * 
 * This class is already complete - no TODOs here!
 */
class QuoteRepository {
    
    private val quotes = listOf(
        "The only way to do great work is to love what you do. - Steve Jobs",
        "Innovation distinguishes between a leader and a follower. - Steve Jobs",
        "Stay hungry, stay foolish. - Steve Jobs",
        "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
        "It is during our darkest moments that we must focus to see the light. - Aristotle",
        "The only impossible journey is the one you never begin. - Tony Robbins",
        "Life is what happens when you're busy making other plans. - John Lennon",
        "The way to get started is to quit talking and begin doing. - Walt Disney"
    )
    
    /**
     * Fetches a random quote.
     * Simulates network delay with coroutine delay.
     * 
     * @return A random quote string
     * @throws Exception if fetching fails (simulated 10% failure rate)
     */
    suspend fun getRandomQuote(): String {
        // Simulate network delay
        delay(1000)
        
        // Simulate occasional failures (10% chance)
        if (Math.random() < 0.1) {
            throw Exception("Network error")
        }
        
        // Return random quote
        return quotes.random()
    }
}
