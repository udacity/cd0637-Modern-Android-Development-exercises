package com.udacity.uitesting.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth().testTag("username_field")
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().testTag("password_field")
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (username == "user" && password == "pass") {
                    onLoginSuccess(username)
                } else {
                    isError = true
                }
            },
            modifier = Modifier.fillMaxWidth().testTag("login_button")
        ) {
            Text("Login")
        }
        if (isError) {
            Text(
                "Invalid Credentials",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp).testTag("error_message")
            )
        }
    }
}
