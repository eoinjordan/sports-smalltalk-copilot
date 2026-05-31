from datetime import datetime, timezone
from typing import List
from .models import SourceFact, Sport


def utc_now() -> str:
    return datetime.now(timezone.utc).isoformat()


MOCK_FACTS: list[SourceFact] = [
    SourceFact(
        source_name="Mock Sports Desk",
        source_url="https://example.com/gaa-weekend-roundup",
        title="GAA weekend throws up selection headaches",
        sport=Sport.gaa,
        summary="Several teams are dealing with form questions and selection debate after a busy weekend of championship fixtures.",
        published_at=utc_now(),
        confidence=0.72,
    ),
    SourceFact(
        source_name="Mock Sports Desk",
        source_url="https://example.com/rugby-depth-chart",
        title="Rugby provinces weigh squad rotation",
        sport=Sport.rugby,
        summary="Coaches are balancing player workload, squad depth, and form as the season reaches a high-pressure phase.",
        published_at=utc_now(),
        confidence=0.75,
    ),
    SourceFact(
        source_name="Mock Sports Desk",
        source_url="https://example.com/football-transfer-watch",
        title="Football sides face familiar consistency questions",
        sport=Sport.football,
        summary="Supporters are debating whether recent results reflect real progress or another short-lived run of form.",
        published_at=utc_now(),
        confidence=0.7,
    ),
    SourceFact(
        source_name="Mock Sports Desk",
        source_url="https://example.com/other-sports-irish-interest",
        title="Irish athletes continue strong international form",
        sport=Sport.other,
        summary="Irish competitors in several sports are attracting more casual attention after strong recent performances.",
        published_at=utc_now(),
        confidence=0.68,
    ),
]


def get_facts(sport: Sport, limit: int = 3) -> list[SourceFact]:
    matching = [fact for fact in MOCK_FACTS if fact.sport == sport]
    if not matching:
        matching = MOCK_FACTS
    return matching[:limit]


def list_sources() -> list[SourceFact]:
    return MOCK_FACTS
