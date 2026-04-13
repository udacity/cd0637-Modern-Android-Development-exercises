package com.udacity.quote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.udacity.quote.ui.QuoteUiState
import com.udacity.quote.ui.QuoteViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuoteScreen()
                }
            }
        }
    }
}

/**
 * QuoteScreen - Main screen displaying quotes.
 * 
 * This composable demonstrates:
 * - Getting ViewModel instance with viewModel()
 * - Observing StateFlow with collectAsState()
 * - Handling different UI states with when expression
 * - Triggering ViewModel events from UI
 */
@Composable
fun QuoteScreen(
    viewModel: QuoteViewModel = viewModel()
) {
    // Observe ViewModel state with collectAsState()
    // This converts StateFlow to Compose State
    // UI will recompose when state changes
    val uiState by viewModel.uiState.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = stringResource(R.string.quote_title),
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Content based on UI state
        // Box with weight takes up available space
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Different UI for each state
            when (uiState) {
                is QuoteUiState.Loading -> {
                    LoadingContent()
                }
                is QuoteUiState.Success -> {
                    QuoteContent((uiState as QuoteUiState.Success).quote)
                }
                is QuoteUiState.Error -> {
                    ErrorContent((uiState as QuoteUiState.Error).message)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Button to fetch new quote
        Button(
            onClick = { viewModel.fetchQuote() }
        ) {
            Text(stringResource(R.string.get_quote_button))
        }
    }
}

/**
 * LoadingContent - Shows loading indicator.
 */
@Composable
fun LoadingContent() {
    CircularProgressIndicator()
}

/**
 * QuoteContent - Displays the quote.
 * 
 * @param quote The quote text to display
 */
@Composable
fun QuoteContent(quote: String) {
    Text(
        text = quote,
        fontSize = 18.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(16.dp)
    )
}

/**
 * ErrorContent - Shows error message.
 * 
 * @param message The error message to display
 */
@Composable
fun ErrorContent(message: String) {
    Text(
        text = "${stringResource(R.string.error_prefix)}$message",
        color = MaterialTheme.colorScheme.error,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun QuoteScreenPreview() {
    MaterialTheme {
        QuoteScreen()
    }
}
