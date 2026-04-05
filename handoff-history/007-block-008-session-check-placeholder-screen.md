## Phase Context
- Active phase: Phase 1 - Session foundation
- Phase objective: Establish a small app shell and screen structure that can grow into the blueprint's splash, login, and protected-home flow without adding session mechanics too early.
- Phase slice status: In progress. The current recorded slice has completed validation setup, focused greeting-test cleanup, greeting screen extraction, home-screen naming alignment, and the first authenticated-vs-unauthenticated app-shell split; the next block should add the missing startup session-check surface before real auth state is introduced.

Next block name
BLOCK 008 - Session Check Placeholder Screen

Objective
Introduce a dedicated startup `SessionCheckScreen` placeholder and extend the tiny app root so the code explicitly represents startup checking, unauthenticated, and authenticated surfaces without introducing real session restoration logic yet.

Relevant files
- AGENTS.md
- handoff/validation-report.md
- app/src/main/java/com/example/baseaiproject/MainActivity.kt
- app/src/main/java/com/example/baseaiproject/AppRoot.kt
- app/src/main/java/com/example/baseaiproject/HomeScreen.kt
- app/src/main/java/com/example/baseaiproject/LoginScreen.kt
- app/src/main/java/com/example/baseaiproject/SessionCheckScreen.kt
- scripts/validate-compile.ps1

Constraints
- Keep the UI minimal and neutral
- Make the startup-check, unauthenticated, and authenticated surfaces explicit in code, but do not introduce real session state, token handling, delays, or navigation library wiring yet
- Preserve the existing home greeting output when the authenticated surface is rendered and keep the login placeholder neutral
- Verify with the smallest meaningful production Kotlin compile target unless the block expands into test-source changes

What not to change
- Do not add ViewModels, repositories, or auth/session models yet
- Do not add Navigation Compose routes or back-stack behavior yet
- Do not change the greeting formatter output or expand the existing placeholder screens into broader product behavior
- Do not add new tests unless the chosen app-root structure creates a focused need

Done criteria
- A dedicated `SessionCheckScreen.kt` exists with neutral placeholder UI and a preview
- `AppRoot.kt` can represent startup checking, unauthenticated, and authenticated placeholder surfaces in a simple local way
- `MainActivity.kt` continues to delegate to the root composable without adding auth/session wiring
- The app still compiles and the home surface still renders the existing greeting text when the authenticated branch is selected
- `handoff/validation-report.md` records the actual validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: direct
- Rationale: The next block is another small placeholder-surface addition and can extend the new local app-shell structure without needing deeper architectural decisions yet.
