package com.example.sportssmalltalk.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportssmalltalk.R
import com.example.sportssmalltalk.data.SourceFact
import com.example.sportssmalltalk.ui.theme.InkBlack
import com.example.sportssmalltalk.ui.theme.MutedText
import com.example.sportssmalltalk.ui.theme.PanelBlack
import com.example.sportssmalltalk.ui.theme.TerminalAmber
import com.example.sportssmalltalk.ui.theme.TerminalGreen
import com.example.sportssmalltalk.vm.AppScreen
import com.example.sportssmalltalk.vm.MainViewModel

private val demoFacts = listOf(
    SourceFact(
        source_name = "The42",
        source_url = "https://www.the42.ie/",
        title = "Dublin edge out Kerry in classic All-Ireland semi-final",
        sport = "GAA",
        summary = "Late point seals it at Croke Park.",
        published_at = "now",
        confidence = 0.8
    ),
    SourceFact(
        source_name = "The42",
        source_url = "https://www.the42.ie/",
        title = "Mayo survive late scare to reach final",
        sport = "GAA",
        summary = "Enough drama to keep the group chat alive.",
        published_at = "now",
        confidence = 0.8
    ),
    SourceFact(
        source_name = "The42",
        source_url = "https://www.the42.ie/",
        title = "Ireland beat South Africa in tight Test",
        sport = "Rugby",
        summary = "Set-piece pressure and late composure decide it.",
        published_at = "now",
        confidence = 0.8
    ),
    SourceFact(
        source_name = "The42",
        source_url = "https://www.the42.ie/",
        title = "Man Utd in chaos again, somehow",
        sport = "Football",
        summary = "The incident queue remains open.",
        published_at = "now",
        confidence = 0.8
    )
)

@Composable
fun SportsSmallTalkApp(viewModel: MainViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = InkBlack,
        bottomBar = {
            BottomNav(
                selected = state.screen,
                onSelected = viewModel::setScreen
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF10180D), InkBlack, Color.Black)
                    )
                )
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                when (state.screen) {
                    AppScreen.Today -> TodayScreen(
                        facts = demoFacts,
                        onGenerate = viewModel::generate
                    )

                    AppScreen.Generator -> GeneratorScreen(
                        sport = state.sport,
                        setting = state.setting,
                        tone = state.tone,
                        loading = state.loading,
                        error = state.error,
                        starter = state.starter,
                        onSport = viewModel::setSport,
                        onSetting = viewModel::setSetting,
                        onTone = viewModel::setTone,
                        onGenerate = viewModel::generate
                    )

                    AppScreen.CheatSheet -> CheatSheetScreen(starter = state.starter)
                    AppScreen.ItCrowd -> ItCrowdScreen(
                        enabled = state.itCrowdMode,
                        onToggle = viewModel::toggleItCrowdMode,
                        onGenerate = viewModel::generate
                    )
                }
            }
        }
    }
}

@Composable
private fun TodayScreen(facts: List<SourceFact>, onGenerate: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
        HeroHeader()
        MockupHeroCard()
        ChoiceRow(
            label = "Today's sport context",
            options = listOf("GAA", "Rugby", "Football", "Other"),
            selected = "GAA",
            onSelected = {}
        )
        StoryCard(fact = facts.first(), featured = true, imageRes = R.drawable.screen_stories)
        StoryCard(fact = facts[1], imageRes = R.drawable.screen_today)
        StoryCard(fact = facts[2], imageRes = R.drawable.screen_generator)
        StoryCard(fact = facts[3], imageRes = R.drawable.screen_cheatsheet)
        ScreenSampleRail()
        RetroButton(text = "Generate pub line", modifier = Modifier.fillMaxWidth(), onClick = onGenerate)
        DisclaimerCard()
    }
}

@Composable
private fun HeroHeader() {
    TerminalCard(title = "") {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("SPORTS", color = TerminalAmber, style = MaterialTheme.typography.headlineLarge)
            Text("SMALL TALK", color = TerminalAmber, style = MaterialTheme.typography.headlineLarge)
            Text("COPILOT", color = TerminalGreen, style = MaterialTheme.typography.headlineLarge)
            Text("Powered by source summaries", color = Color(0xFFFFE7A3), style = MaterialTheme.typography.bodyMedium)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                PixelLogo()
                Spacer(Modifier.width(16.dp))
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFEED9A5),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFB58F4B))
                ) {
                    Text(
                        "People.\nWhat a bunch of bastards.",
                        modifier = Modifier.padding(14.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Text(
                "We'll help you talk sports so you don't have to.",
                modifier = Modifier.padding(top = 16.dp),
                color = Color(0xFFFFE7A3),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun GeneratorScreen(
    sport: String,
    setting: String,
    tone: String,
    loading: Boolean,
    error: String?,
    starter: com.example.sportssmalltalk.data.ConversationStarter?,
    onSport: (String) -> Unit,
    onSetting: (String) -> Unit,
    onTone: (String) -> Unit,
    onGenerate: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Text("Conversation Generator", color = TerminalGreen, style = MaterialTheme.typography.headlineMedium)
        ChoiceRow("Sport", listOf("GAA", "Rugby", "Football", "Other"), sport, onSport)
        ChoiceRow("Scene", listOf("Pub", "Office Kitchen", "Taxi", "Family Event"), setting, onSetting)
        ChoiceRow("Tone", listOf("Safe", "Funny", "Nerdy", "Confident"), tone, onTone)
        RetroButton(
            text = if (loading) "Generating..." else "Generate pub line",
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading,
            onClick = onGenerate
        )
        if (loading) CircularProgressIndicator(color = TerminalGreen)
        error?.let { Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium) }
        starter?.let { StarterCard(it) }
        ScreenSampleRail()
        FriendFacePanel()
    }
}

@Composable
private fun CheatSheetScreen(starter: com.example.sportssmalltalk.data.ConversationStarter?) {
    Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Text("Cheat Sheet", color = TerminalGreen, style = MaterialTheme.typography.headlineMedium)
        CheatSheetCard(starter)
        MockupHeroCard()
        DisclaimerCard()
    }
}

@Composable
private fun ItCrowdScreen(enabled: Boolean, onToggle: () -> Unit, onGenerate: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("IT Crowd Mode", color = TerminalGreen, style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.weight(1f))
            Switch(
                checked = enabled,
                onCheckedChange = { onToggle() },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = TerminalAmber,
                    checkedTrackColor = TerminalGreen,
                    uncheckedThumbColor = MutedText,
                    uncheckedTrackColor = PanelBlack
                )
            )
        }
        FriendFacePanel()
        TerminalCard(title = "Survival protocol") {
            Text("1. Ask one open question.", color = MutedText, style = MaterialTheme.typography.bodyMedium)
            Text("2. Make one harmless IT analogy.", color = MutedText, style = MaterialTheme.typography.bodyMedium)
            Text("3. Do not mention xG unless you can define it.", color = MutedText, style = MaterialTheme.typography.bodyMedium)
            Text("4. Leave before someone asks your county.", color = MutedText, style = MaterialTheme.typography.bodyMedium)
        }
        ScreenSampleRail()
        RetroButton(text = "Give me another line", modifier = Modifier.fillMaxWidth(), onClick = onGenerate)
    }
}

@Composable
private fun BottomNav(selected: AppScreen, onSelected: (AppScreen) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF070907))
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        AppScreen.values().forEach { screen ->
            val active = screen == selected
            Column(
                modifier = Modifier
                    .clickable { onSelected(screen) }
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = when (screen) {
                        AppScreen.Today -> "01"
                        AppScreen.Generator -> "02"
                        AppScreen.CheatSheet -> "03"
                        AppScreen.ItCrowd -> "04"
                    },
                    color = if (active) TerminalGreen else MutedText,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = screen.label,
                    color = if (active) TerminalGreen else MutedText,
                    style = MaterialTheme.typography.bodySmall
                )
                if (active) {
                    Spacer(
                        Modifier
                            .height(2.dp)
                            .width(32.dp)
                            .background(TerminalGreen)
                    )
                }
            }
        }
    }
}
