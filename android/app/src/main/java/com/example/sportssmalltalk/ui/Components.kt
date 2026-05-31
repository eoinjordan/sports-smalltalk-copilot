package com.example.sportssmalltalk.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sportssmalltalk.data.ConversationStarter
import com.example.sportssmalltalk.data.SourceFact
import com.example.sportssmalltalk.ui.theme.MutedText
import com.example.sportssmalltalk.ui.theme.Paper
import com.example.sportssmalltalk.ui.theme.PaperInk
import com.example.sportssmalltalk.ui.theme.PanelSoft
import com.example.sportssmalltalk.ui.theme.TerminalAmber
import com.example.sportssmalltalk.ui.theme.TerminalGreen

@Composable
fun PixelLogo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        listOf(
            " ▄▄▄ ",
            "█o o█",
            "█ ▄ █",
            "▀███▀",
            " /|\\ "
        ).forEach { line ->
            Text(line, color = TerminalAmber, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun RetroButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(52.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = TerminalAmber,
            contentColor = Color.Black,
            disabledContainerColor = Color(0xFF4E3A16),
            disabledContentColor = Color(0xFF9C8A67)
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text.uppercase(), style = MaterialTheme.typography.labelLarge)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChoiceRow(
    label: String,
    options: List<String>,
    selected: String,
    onSelected: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = label.uppercase(), color = TerminalGreen, style = MaterialTheme.typography.labelLarge)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            options.forEach { option ->
                val active = option == selected
                Surface(
                    modifier = Modifier.clickable { onSelected(option) },
                    shape = RoundedCornerShape(8.dp),
                    color = if (active) TerminalGreen else Color(0xFF171A17),
                    border = BorderStroke(1.dp, if (active) TerminalGreen else Color(0xFF30392C))
                ) {
                    Text(
                        text = option,
                        modifier = Modifier.padding(horizontal = 13.dp, vertical = 10.dp),
                        color = if (active) Color.Black else Color.White,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
fun TerminalCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable Column.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, Color(0xFF2A3624))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(title.uppercase(), color = TerminalGreen, style = MaterialTheme.typography.titleMedium)
            content()
        }
    }
}

@Composable
fun StoryCard(fact: SourceFact, featured: Boolean = false) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = if (featured) Paper else PanelSoft),
        border = BorderStroke(1.dp, if (featured) Color(0xFFB58F4B) else Color(0xFF2B3628))
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(if (featured) Color(0xFF253015) else TerminalGreen),
                    contentAlignment = Alignment.Center
                ) {
                    Text("42", color = if (featured) TerminalGreen else Color.Black, style = MaterialTheme.typography.bodySmall)
                }
                Spacer(Modifier.width(8.dp))
                Text(
                    text = fact.source_name,
                    color = if (featured) PaperInk else MutedText,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.weight(1f))
                Text("2h ago", color = if (featured) PaperInk else MutedText, style = MaterialTheme.typography.bodySmall)
            }
            Text(
                text = fact.title,
                color = if (featured) PaperInk else Color.White,
                style = if (featured) MaterialTheme.typography.titleLarge else MaterialTheme.typography.titleMedium
            )
            Text(
                text = fact.summary,
                color = if (featured) PaperInk else MutedText,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun StarterCard(starter: ConversationStarter) {
    TerminalCard(title = "Today's opening line") {
        Text("“${starter.opening_line}”", style = MaterialTheme.typography.bodyLarge)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SmallAction("Explain it") {}
            SmallAction("Follow up") {}
        }
    }
}

@Composable
fun CheatSheetCard(starter: ConversationStarter?) {
    val opening = starter?.opening_line ?: "Generate a line first, then this becomes your pub survival sheet."
    val explainer = starter?.thirty_second_explainer ?: "No generated context yet. The safe move is to ask what someone else made of the match."
    val follow = starter?.follow_up_question ?: "Did you see much of it, or just the headlines?"
    val avoid = starter?.avoid_saying ?: "Avoid pretending you watched the full game."

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Paper),
        border = BorderStroke(1.dp, Color(0xFFB58F4B))
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PaperSection("What happened", opening)
            PaperSection("Why people care", explainer)
            PaperSection("One question to ask", follow)
            PaperSection("Avoid saying", avoid)
        }
    }
}

@Composable
private fun PaperSection(title: String, body: String) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(title.uppercase(), color = PaperInk, style = MaterialTheme.typography.titleMedium)
        Text(body, color = PaperInk, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun SmallAction(text: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFF141814),
        border = BorderStroke(1.dp, Color(0xFF394632))
    ) {
        Text(
            text = text.uppercase(),
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FriendFacePanel() {
    TerminalCard(title = "IT Crowd mode") {
        Row(verticalAlignment = Alignment.CenterVertically) {
            PixelLogo()
            Spacer(Modifier.width(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Friend Face-adjacent banter engine", color = TerminalGreen, style = MaterialTheme.typography.titleMedium)
                Text(
                    "For people who know more about packet loss than league tables.",
                    color = MutedText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Text(
            "I came here to avoid conversation. Unfortunately, I have generated a perfect opening line.",
            color = TerminalGreen,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun DisclaimerCard() {
    TerminalCard(title = "disclaimer.exe") {
        Text(
            "This app does not make you a sports expert. It gives you just enough context to nod safely, ask a decent question, and leave before extra time.",
            color = MutedText,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
