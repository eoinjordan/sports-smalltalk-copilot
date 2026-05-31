import json
import os
from typing import Any
import httpx
from .models import SourceFact, ConversationStarter, GenerateRequest
from .prompt_templates import SYSTEM_PROMPT, build_prompt


def facts_to_text(facts: list[SourceFact]) -> str:
    lines: list[str] = []
    for index, fact in enumerate(facts, start=1):
        lines.append(
            f"{index}. Title: {fact.title}\n"
            f"   Source: {fact.source_name}\n"
            f"   URL: {fact.source_url}\n"
            f"   Summary: {fact.summary}\n"
            f"   Published: {fact.published_at}"
        )
    return "\n".join(lines)


def mock_generate(request: GenerateRequest, facts: list[SourceFact]) -> dict[str, str]:
    fact = facts[0] if facts else None
    topic = fact.summary if fact else "There is a sports story people may be discussing."
    sport = request.sport.value
    tone = request.tone.value.lower()

    opener = f"I saw a bit of chat about the {sport} story — sounds like it is more about momentum and decision-making than one single moment."
    if tone == "nerdy":
        opener = f"The {sport} discussion feels like a production incident: everyone agrees something happened, but the root cause analysis depends on who you ask."
    elif tone == "funny":
        opener = f"I am not pretending I watched every minute, but the {sport} discourse seems to have entered full group-chat meltdown."
    elif tone == "confident":
        opener = f"The main {sport} talking point seems to be whether this is a real trend or just one noisy result."

    return {
        "opening_line": opener,
        "it_analogy": "It is like debugging a system where the logs show the error, but the architecture decisions explain why it keeps happening.",
        "thirty_second_explainer": f"The safe read is: {topic} The useful angle is to talk about form, workload, confidence, or decision-making rather than making a hard claim.",
        "follow_up_question": "Did you watch much of it, or are you mostly seeing the reaction after the fact?",
        "avoid_saying": "Avoid pretending you saw the full match or quoting specific stats unless they are in the source card.",
        "risk_level": "safe",
    }


async def openai_compatible_generate(request: GenerateRequest, facts: list[SourceFact]) -> dict[str, Any]:
    base_url = os.getenv("LLM_BASE_URL", "http://localhost:11434/v1").rstrip("/")
    api_key = os.getenv("LLM_API_KEY", "not-needed-for-local")
    model = os.getenv("LLM_MODEL", "qwen2.5:0.5b-instruct")
    prompt = build_prompt(facts_to_text(facts), request.sport.value, request.setting.value, request.tone.value)

    payload = {
        "model": model,
        "messages": [
            {"role": "system", "content": SYSTEM_PROMPT},
            {"role": "user", "content": prompt},
        ],
        "temperature": 0.5,
        "response_format": {"type": "json_object"},
    }

    async with httpx.AsyncClient(timeout=30) as client:
        response = await client.post(
            f"{base_url}/chat/completions",
            headers={"Authorization": f"Bearer {api_key}", "Content-Type": "application/json"},
            json=payload,
        )
        response.raise_for_status()
        content = response.json()["choices"][0]["message"]["content"]
        return json.loads(content)


async def generate_starter(request: GenerateRequest, facts: list[SourceFact]) -> ConversationStarter:
    provider = os.getenv("LLM_PROVIDER", "mock")
    if provider == "openai_compatible":
        try:
            generated = await openai_compatible_generate(request, facts)
        except Exception:
            generated = mock_generate(request, facts)
    else:
        generated = mock_generate(request, facts)

    return ConversationStarter(
        opening_line=generated.get("opening_line", "There is a sports story doing the rounds, but I would keep the take cautious."),
        it_analogy=generated.get("it_analogy", "It is like a system where symptoms are obvious but root cause needs more context."),
        thirty_second_explainer=generated.get("thirty_second_explainer", "The safest approach is to ask what others made of it before offering a firm opinion."),
        follow_up_question=generated.get("follow_up_question", "What did you make of it?"),
        avoid_saying=generated.get("avoid_saying", "Avoid specific claims you cannot source."),
        risk_level=generated.get("risk_level", "safe"),
        sources_used=facts,
    )
