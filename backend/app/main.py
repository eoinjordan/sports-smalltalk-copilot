from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from dotenv import load_dotenv
from .models import GenerateRequest, ConversationStarter, SourceList
from .sources import get_facts, list_sources
from .llm import generate_starter

load_dotenv()

app = FastAPI(
    title="Sports Small Talk Copilot API",
    description="Source-aware conversation starter generator for sports small talk.",
    version="1.0.0",
)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.get("/health")
async def health() -> dict[str, str]:
    return {"status": "ok"}


@app.get("/sources", response_model=SourceList)
async def sources() -> SourceList:
    return SourceList(sources=list_sources())


@app.post("/generate", response_model=ConversationStarter)
async def generate(request: GenerateRequest) -> ConversationStarter:
    facts = get_facts(request.sport, request.source_limit)
    return await generate_starter(request, facts)
