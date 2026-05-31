package com.example.sportssmalltalk.data

data class SourceFact(
    val source_name: String,
    val source_url: String,
    val title: String,
    val sport: String,
    val summary: String,
    val published_at: String,
    val confidence: Double
)

data class GenerateRequest(
    val sport: String,
    val setting: String,
    val tone: String,
    val source_limit: Int = 3
)

data class ConversationStarter(
    val opening_line: String,
    val it_analogy: String,
    val thirty_second_explainer: String,
    val follow_up_question: String,
    val avoid_saying: String,
    val risk_level: String,
    val sources_used: List<SourceFact>
)
