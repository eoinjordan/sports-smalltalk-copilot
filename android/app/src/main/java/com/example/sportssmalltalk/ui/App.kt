package com.example.sportssmalltalk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportssmalltalk.vm.MainViewModel

@Composable
fun SportsSmallTalkApp(viewModel: MainViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text("Sports Small Talk Copilot", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Enough context to survive the conversation. Not enough confidence to start a fight.",
            style = MaterialTheme.typography.bodyMedium
        )

        ChoiceRow(
            label = "Sport",
            options = listOf("GAA", "Rugby", "Football", "Other"),
            selected = state.sport,
            onSelected = viewModel::setSport
        )

        ChoiceRow(
            label = "Setting",
            options = listOf("Pub", "Office Kitchen", "Taxi", "Family Event"),
            selected = state.setting,
            onSelected = viewModel::setSetting
        )

        ChoiceRow(
            label = "Tone",
            options = listOf("Safe", "Funny", "Nerdy", "Confident"),
            selected = state.tone,
            onSelected = viewModel::setTone
        )

        Button(
            onClick = viewModel::generate,
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.loading
        ) {
            Text(if (state.loading) "Generating..." else "Generate conversation starter")
        }

        if (state.loading) {
            CircularProgressIndicator()
        }

        state.error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        state.starter?.let {
            Spacer(Modifier.height(8.dp))
            StarterCard(it)
        }

        Spacer(Modifier.weight(1f))
        Text(
            "Inspired by Bluffball.co.uk",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
