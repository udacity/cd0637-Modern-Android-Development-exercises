package com.example.demo_ui_testing.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.demo_ui_testing.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * LoginScreen demonstrates common UI patterns for authentication.
 * Includes loading states, error handling, and test tags for UI automation.
 */
@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.login_title),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { 
                username = it
                errorMessage = null 
            },
            label = { Text(stringResource(R.string.username_label)) },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("username_field"),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = password,
            onValueChange = { 
                password = it
                errorMessage = null
            },
            label = { Text(stringResource(R.string.password_label)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("password_field"),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.testTag("loading_indicator")
            )
            Text(
                text = stringResource(R.string.loading_message),
                modifier = Modifier.padding(top = 8.dp)
            )
        } else {
            Button(
                onClick = {
                    scope.launch {
                        isLoading = true
                        // Simulate network delay for async testing
                        delay(2000)
                        isLoading = false
                        
                        if (username == "admin" && password == "password123") {
                            onLoginSuccess(username)
                        } else {
                            errorMessage = "Invalid Credentials"
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("login_button"),
                enabled = username.isNotBlank() && password.isNotBlank()
            ) {
                Text(stringResource(R.string.login_button_label))
            }
        }
        
        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .testTag("error_message")
            )
        }
    }
}
