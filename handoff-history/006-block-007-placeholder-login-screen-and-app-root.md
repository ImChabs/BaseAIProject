## Phase Context
- Active phase: Phase 1 - Session foundation
- Phase objective: Establish a small app shell and screen structure that can grow into the blueprint's splash, login, and protected-home flow without adding session mechanics too early.
- Phase slice status: In progress. The current recorded slice has completed validation setup, focused greeting-test cleanup, greeting screen extraction, and home-screen naming alignment; the next block should introduce an explicit unauthenticated placeholder surface and a tiny app root without pulling in real session logic yet.

Next block name
BLOCK 007 - Placeholder Login Screen And App Root

Objective
Introduce a dedicated `LoginScreen` placeholder and a small top-level app root composable that makes the app's current authenticated-vs-unauthenticated surface split explicit, while keeping the implementation free of real auth/session behavior.

Relevant files
- AGENTS.md
- handoff/validation-report.md
- app/src/main/java/com/example/baseaiproject/MainActivity.kt
- app/src/main/java/com/example/baseaiproject/HomeScreen.kt
- app/src/main/java/com/example/baseaiproject/LoginScreen.kt
- app/src/main/java/com/example/baseaiproject/AppRoot.kt
- scripts/validate-compile.ps1

Constraints
- Keep the UI minimal and neutral
- Make the authenticated and unauthenticated surfaces explicit in code, but do not introduce real session state, token handling, or navigation library wiring yet
- Preserve the existing home greeting output when the authenticated surface is rendered
- Verify with the smallest meaningful production Kotlin compile target unless the block expands into test-source changes

What not to change
- Do not add ViewModels, repositories, or auth/session models yet
- Do not add Navigation Compose routes or back-stack behavior yet
- Do not change the greeting formatter output or expand the home screen into broader product behavior
- Do not add new tests unless the chosen app-root structure creates a focused need

Done criteria
- A dedicated `LoginScreen.kt` exists with neutral placeholder UI and a preview
- `MainActivity.kt` delegates to a small root composable rather than directly invoking `HomeScreen`
- The new root makes the app's unauthenticated vs authenticated surface split explicit in a simple, local, non-session-aware way
- The app still compiles and the home surface still renders the existing greeting text when selected
- `handoff/validation-report.md` records the actual validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: plan_first
- Rationale: The next block is still small, but it introduces the first app-shell split and should choose a simple structure that prepares for later session state without premature navigation or auth complexity.
