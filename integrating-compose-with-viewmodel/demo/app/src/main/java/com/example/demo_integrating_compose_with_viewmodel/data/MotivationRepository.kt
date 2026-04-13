package com.example.demo_integrating_compose_with_viewmodel.data

import kotlinx.coroutines.delay


class MotivationRepository {
    private val quotes = listOf(
        MotivationQuote("The only way to do great work is to love what you do.", "Steve Jobs"),
        MotivationQuote("Believe you can and you're halfway there.", "Theodore Roosevelt"),
        MotivationQuote("It does not matter how slowly you go as long as you do not stop.", "Confucius"),
        MotivationQuote("Everything you've ever wanted is on the other side of fear.", "George Addair"),
        MotivationQuote("Success is not final, failure is not fatal: it is the courage to continue that counts.", "Winston Churchill"),
        MotivationQuote("Hardships often prepare ordinary people for an extraordinary destiny.", "C.S. Lewis"),
        MotivationQuote("Believe in yourself and all that you are. Know that there is something inside you that is greater than any obstacle.", "Christian D. Larson")
    )

    suspend fun getRandomQuote(): MotivationQuote {
        // Simulate network delay
        delay(1500)
        return quotes.random()
    }
}
