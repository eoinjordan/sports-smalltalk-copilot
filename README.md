# Sports Small Talk Copilot

An IT-Crowd-inspired Android app concept for technical people who want enough sports context to survive a pub, office, taxi, or family conversation without pretending to be a pundit.

Inspired by the "Are We Not Men?" episode (Season 3, Episode 2) of *The IT Crowd* and the legendary [Bluffball.co.uk](https://www.youtube.com/watch?v=f27IqVo5-Oc) website.

> "The thing about Arsenal is, they always try to walk it in!"

The app has two parts:

- `android/` — Kotlin + Jetpack Compose Android client
- `backend/` — FastAPI service that normalises sports headlines/facts and generates conversation starters with an LLM-compatible prompt

The default backend uses mock Irish sports data. Add approved feeds, APIs, or manually curated source URLs before production use. Do not scrape full copyrighted articles. Store titles, source URLs, timestamps, and short factual summaries only.

## Features

- Sport categories: GAA, Rugby, Football, Other
- Context modes: Pub, Office Kitchen, Taxi, Family Event
- Tone modes: Safe, Funny, Nerdy, Confident
- Conversation cards:
  - opening line
  - IT analogy
  - 30-second explanation
  - safe follow-up question
  - avoid saying
- Source-aware output generation
- Mock backend with deterministic fallback
- Optional OpenAI-compatible API support via environment variables

## Repo structure

```text
sports-smalltalk-copilot/
├── android/
│   ├── app/
│   │   ├── build.gradle.kts
│   │   └── src/main/
│   │       ├── AndroidManifest.xml
│   │       └── java/com/example/sportssmalltalk/
│   │           ├── MainActivity.kt
│   │           ├── data/ApiModels.kt
│   │           ├── data/SportsApi.kt
│   │           ├── data/SportsRepository.kt
│   │           ├── ui/App.kt
│   │           ├── ui/Components.kt
│   │           ├── ui/theme/Theme.kt
│   │           └── vm/MainViewModel.kt
│   ├── build.gradle.kts
│   ├── settings.gradle.kts
│   └── gradle.properties
├── backend/
│   ├── app/
│   │   ├── main.py
│   │   ├── models.py
│   │   ├── llm.py
│   │   ├── sources.py
│   │   └── prompt_templates.py
│   ├── requirements.txt
│   └── .env.example
└── docs/
    ├── product-plan.md
    └── legal-and-data-notes.md
```

## Run the backend

```bash
cd backend
python3 -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
uvicorn app.main:app --reload --host 0.0.0.0 --port 8000
```

Test:

```bash
curl http://localhost:8000/health
curl http://localhost:8000/sources
curl -X POST http://localhost:8000/generate \
  -H 'Content-Type: application/json' \
  -d '{"sport":"GAA","setting":"Pub","tone":"Nerdy"}'
```

## Optional LLM configuration

The backend works without a remote LLM. To use an OpenAI-compatible endpoint:

```bash
export LLM_PROVIDER=openai_compatible
export LLM_BASE_URL=https://api.openai.com/v1
export LLM_API_KEY=YOUR_KEY
export LLM_MODEL=gpt-4.1-mini
```

For local models, point `LLM_BASE_URL` at an OpenAI-compatible server such as LM Studio, Ollama with OpenAI compatibility, vLLM, or llama.cpp server.

## Run the Android app

Open `android/` in Android Studio.

For emulator, the backend URL defaults to:

```text
http://10.0.2.2:8000
```

For a physical phone on the same Wi-Fi, change `BASE_URL` in:

```text
android/app/src/main/java/com/example/sportssmalltalk/data/SportsApi.kt
```

to your laptop IP address, for example:

```kotlin
private const val BASE_URL = "http://192.168.1.50:8000"
```

## Production notes

- Add attribution and source links in the UI.
- Do not copy full article text.
- Prefer APIs/RSS/licensed feeds/manual editorial snippets.
- Never generate fake scores, quotes, fixtures, injuries, or stats.
- Add a visible disclaimer: “Generated from source summaries. Check linked sources for full context.”

## Suggested app name options

- Sports Small Talk Copilot
- Pub Context Compiler
- Matchday Debugger
- The Banter Stack
- SmallTalk FC
