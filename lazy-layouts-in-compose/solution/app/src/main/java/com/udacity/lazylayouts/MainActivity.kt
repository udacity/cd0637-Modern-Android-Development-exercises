package com.udacity.lazylayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PresidentsApp() {
    val presidents = PresidentData.presidents
    // Group presidents by Poltical Party for the sticky headers
    val groupedPresidents = remember(presidents) { presidents.groupBy { it.party } }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        groupedPresidents.forEach { (party, presidentsInParty) ->
            // Sticky Header for the Party Name
            stickyHeader {
                PartyHeader(partyName = party)
            }

            // List items for presidents in that party
            items(
                items = presidentsInParty,
                key = { it.id } // Use ID as key for performance
            ) { president ->
                PresidentListItem(president = president)
            }
        }
    }
}

@Composable
fun PartyHeader(partyName: String) {
    Surface(
        color = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = partyName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }
}

@Composable
fun PresidentListItem(president: President, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = president.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = president.yearsInOffice,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = president.description,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        PresidentsApp()
    }
}
