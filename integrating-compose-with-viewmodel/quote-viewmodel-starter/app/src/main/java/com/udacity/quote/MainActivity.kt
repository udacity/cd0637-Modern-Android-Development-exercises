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
import androidx.lifecycle.viewmodel.compose.viewModel
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
 * TODO: Complete this composable to observe ViewModel state
 */
@Composable
fun QuoteScreen(
    viewModel: QuoteViewModel = viewModel()
) {
    // TODO: Step 1 - Get ViewModel and observe state
    // Use collectAsState() to observe the ViewModel's uiState
    // val uiState by viewModel.uiState.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.quote_title),
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // TODO: Step 2 - Handle different UI states
        // Use a when expression to display different UI based on uiState
        // when (uiState) {
        //     is QuoteUiState.Loading -> {
        //         // Show loading indicator
        //     }
        //     is QuoteUiState.Success -> {
        //         // Show quote text
        //     }
        //     is QuoteUiState.Error -> {
        //         // Show error message
        //     }
        // }
        
        // Placeholder content - replace with when expression
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "TODO: Display quote based on state",
                textAlign = TextAlign.Center
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // TODO: Step 3 - Add button to fetch new quote
        Button(
            onClick = { /* TODO: Call viewModel.fetchQuote() */ }
        ) {
            Text(stringResource(R.string.get_quote_button))
        }
    }
}

// TODO: Uncomment when QuoteUiState is defined
// @Composable
// fun LoadingContent() {
//     CircularProgressIndicator()
// }
//
// @Composable
// fun QuoteContent(quote: String) {
//     Text(
//         text = quote,
//         fontSize = 18.sp,
//         fontStyle = FontStyle.Italic,
//         textAlign = TextAlign.Center,
//         modifier = Modifier.padding(16.dp)
//     )
// }
//
// @Composable
// fun ErrorContent(message: String) {
//     Text(
//         text = "${stringResource(R.string.error_prefix)}$message",
//         color = MaterialTheme.colorScheme.error,
//         textAlign = TextAlign.Center,
//         modifier = Modifier.padding(16.dp)
//     )
// }

@Preview(showBackground = true)
@Composable
fun QuoteScreenPreview() {
    MaterialTheme {
        QuoteScreen()
    }
}
