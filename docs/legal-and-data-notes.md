# Legal and data notes

## Do not ship full article scraping by default

The app should not copy, store, or redistribute full publisher articles. Keep the production data layer limited to:

- title
- source name
- source URL
- publication timestamp
- short factual summary written by your system or manually supplied
- category/sport

## Preferred source approaches

1. Official APIs or licensed feeds.
2. RSS feeds where allowed by the publisher terms.
3. Manual editorial input.
4. User-supplied links with minimal extracted metadata.

## UI attribution

Every generated card should show source names and links. Add a footer:

Generated from source summaries. Check linked sources for full context.

## Prompt safety

The model must not invent:

- scores
- fixtures
- injuries
- quotes
- transfer rumours
- disciplinary incidents
- allegations
- personal information

## Tone safety

Avoid sectarian, abusive, or baiting language. Irish sport can be locally sensitive. The app should prefer cautious, question-led starters over hard opinions.
