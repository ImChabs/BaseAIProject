## Phase Context
- Active phase: Phase 1 - Session foundation
- Phase objective: Establish a small app shell and screen structure that can grow into the blueprint's splash, login, and protected-home flow without adding session mechanics too early.
- Phase slice status: New slice. The placeholder-screen slice is now complete, so the next block should begin the phase's explicit auth/session state modeling without introducing real persistence, login, or token behavior yet.

Next block name
BLOCK 010 - Explicit App Auth State Model

Objective
Replace the current placeholder surface enums with one small explicit app auth/session state model that clearly represents startup checking, unauthenticated, and authenticated app states and keeps the protected destination selection compatible with later session wiring.

Relevant files
- AGENTS.md
- handoff/validation-report.md
- app/src/main/java/com/example/baseaiproject/AppRoot.kt
- app/src/main/java/com/example/baseaiproject/MainActivity.kt
- app/src/main/java/com/example/baseaiproject/HomeScreen.kt
- app/src/main/java/com/example/baseaiproject/LoginScreen.kt
- app/src/main/java/com/example/baseaiproject/SessionCheckScreen.kt
- app/src/main/java/com/example/baseaiproject/SensitiveScreen.kt
- app/src/main/java/com/example/baseaiproject/SessionSecurityScreen.kt
- scripts/validate-compile.ps1

Constraints
- Keep the UI minimal and neutral
- Introduce one explicit app auth/session state model, but do not add real token handling, persistence, repositories, or ViewModels yet
- Keep any authenticated destination selection tiny and compatible with later navigation work
- Preserve the existing home greeting output and the current neutral placeholder copy unless a rename is required for the new state model
- Verify with the smallest meaningful production Kotlin compile target unless the block expands into test-source changes

What not to change
- Do not implement real login, logout, session restoration, or token refresh behavior yet
- Do not add Navigation Compose routes or back-stack behavior yet
- Do not change the greeting formatter output or expand the placeholder screens into broader product behavior
- Do not add new tests unless the chosen app-root structure creates a focused need

Done criteria
- `AppRoot.kt` renders from one explicit app auth/session state model instead of loosely related placeholder enums
- The model clearly covers startup checking, unauthenticated, and authenticated cases while still allowing the protected placeholder surfaces to be selected in a small local way
- The app still compiles and the existing home greeting text remains unchanged on the home surface
- `handoff/validation-report.md` records the actual validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: direct
- Rationale: The next step is still a small app-shell refactor, but it starts the repo's real auth/session-state shape and should stay precise and bounded.
