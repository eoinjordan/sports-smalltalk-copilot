# Product plan

## Inspiration

This project is a modern, AI-powered implementation of the "Bluffball" concept from *The IT Crowd* (Season 3, Episode 2, "Are We Not Men?"). The goal is to provide a "Banter Stack" that helps technical people integrate into sports-heavy social environments without the fear of being found out.

## Concept

A source-aware Android app that turns sports headlines and short factual summaries into safe, casual conversation starters for technical people.

The product joke is not that the user becomes a sports expert. The joke is that the user gets enough context to participate without hallucinating details or pretending to have watched the match.

## User journey

1. User opens app before entering a social setting.
2. User picks sport, setting, and tone.
3. App requests a generated starter from the backend.
4. User receives:
   - opening line
   - IT analogy
   - 30-second explainer
   - follow-up question
   - avoid saying
5. User can tap source links to read more.

## MVP requirements

- Android Compose app
- Backend API
- Mock data fallback
- OpenAI-compatible LLM mode
- Source citations in output
- Cautious prompt rules
- Clear disclaimer

## Version 1.1 backlog

- Add source adapter interface
- Add RSS support where licensed or permitted
- Add manual source entry screen
- Add sharing cards
- Add local LLM mode on-device or LAN
- Add user profile: teams to avoid, sports they know, tone preference
- Add “explain the rules” micro-lessons
- Add “dangerous take detector”

## Version 2.0 backlog

- Android widget for matchday summaries
- Wear OS companion prompt cards
- Telegram bot mode
- Voice mode for quick prep
- Offline cached packs
- Local embedding search over approved source summaries
