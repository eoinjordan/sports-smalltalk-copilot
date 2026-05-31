from enum import Enum
from pydantic import BaseModel, Field, HttpUrl
from typing import List, Optional


class Sport(str, Enum):
    gaa = "GAA"
    rugby = "Rugby"
    football = "Football"
    other = "Other"


class Setting(str, Enum):
    pub = "Pub"
    office_kitchen = "Office Kitchen"
    taxi = "Taxi"
    family_event = "Family Event"


class Tone(str, Enum):
    safe = "Safe"
    funny = "Funny"
    nerdy = "Nerdy"
    confident = "Confident"


class SourceFact(BaseModel):
    source_name: str
    source_url: str
    title: str
    sport: Sport
    summary: str
    published_at: str
    confidence: float = Field(default=0.8, ge=0.0, le=1.0)


class GenerateRequest(BaseModel):
    sport: Sport = Sport.gaa
    setting: Setting = Setting.pub
    tone: Tone = Tone.safe
    source_limit: int = Field(default=3, ge=1, le=8)


class ConversationStarter(BaseModel):
    opening_line: str
    it_analogy: str
    thirty_second_explainer: str
    follow_up_question: str
    avoid_saying: str
    risk_level: str
    sources_used: list[SourceFact]


class SourceList(BaseModel):
    sources: list[SourceFact]
