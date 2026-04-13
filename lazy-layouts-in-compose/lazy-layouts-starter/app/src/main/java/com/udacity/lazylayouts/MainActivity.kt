package com.udacity.lazylayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.udacity.lazylayouts.data.President
import com.udacity.lazylayouts.data.PresidentData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PresidentsApp()
                }
            }
        }
    }
}

@Composable
fun PresidentsApp() {
    val presidents = PresidentData.presidents
    
    // TODO: Step 1 - Group the presidents by party
    // hint: use presidents.groupBy { it.party }

    // TODO: Step 2 - Create a LazyColumn
    // Inside the LazyColumn:
    //  - Iterate through the grouped data
    //  - Use stickyHeader { } for the party sections
    //  - Use items(items = ...) { } for the presidents in each group
    
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Replace this Box with your LazyColumn implementation")
    }
}

@Composable
fun PresidentListItem(president: President) {
    // TODO: Step 3 - Design the list item
    // Use a Card or Row to display:
    // - Name (Bold)
    // - Years in Office (Gray/Secondary color)
    // - Description (Body text)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        PresidentsApp()
    }
}
