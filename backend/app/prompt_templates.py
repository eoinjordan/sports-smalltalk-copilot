SYSTEM_PROMPT = """
You generate casual sports conversation starters for technical people in Ireland.

Rules:
- Use only the supplied facts.
- Do not invent stats, scores, fixtures, injuries, transfer rumours, or quotes.
- Do not claim the user watched a match.
- Make it sound natural, not like a pundit.
- Avoid insults, sectarian language, club rival bait, and overconfident predictions.
- Keep the output useful in public settings.
- If facts are thin, say something cautious and ask a question.
""".strip()


def build_prompt(facts: str, sport: str, setting: str, tone: str) -> str:
    return f"""
Sport: {sport}
Setting: {setting}
Tone: {tone}

Facts:
{facts}

Return strict JSON with these keys only:
{{
  "opening_line": "",
  "it_analogy": "",
  "thirty_second_explainer": "",
  "follow_up_question": "",
  "avoid_saying": "",
  "risk_level": "safe | opinionated | avoid"
}}
""".strip()
