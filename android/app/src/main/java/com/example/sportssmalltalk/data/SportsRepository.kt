package com.example.sportssmalltalk.data

class SportsRepository(
    private val api: SportsApi = SportsApi.create()
) {
    suspend fun generate(sport: String, setting: String, tone: String): ConversationStarter {
        return api.generate(
            GenerateRequest(
                sport = sport,
                setting = setting,
                tone = tone,
                source_limit = 3
            )
        )
    }
}
