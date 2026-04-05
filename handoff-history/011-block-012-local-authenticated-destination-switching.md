## Phase Context
- Active phase: Phase 1 - Session foundation
- Phase objective: Establish a small app shell and screen structure that can grow into the blueprint's splash, login, and protected-home flow without adding session mechanics too early.
- Phase slice status: In progress. The placeholder auth flow is now exercisable from startup check to login to authenticated home, but the authenticated placeholder shell still needs explicit local switching across the protected surfaces it already models.

Next block name
BLOCK 012 - Local Authenticated Destination Switching

Objective
Use the existing `AuthenticatedDestination` model to add tiny explicit switching between the authenticated placeholder surfaces so home, sensitive, and session/security can be reached locally without introducing Navigation Compose, back-stack behavior, or real session logic.

Relevant files
- AGENTS.md
- handoff/validation-report.md
- app/src/main/java/com/example/baseaiproject/AppRoot.kt
- app/src/main/java/com/example/baseaiproject/MainActivity.kt
- app/src/main/java/com/example/baseaiproject/HomeScreen.kt
- app/src/main/java/com/example/baseaiproject/SensitiveScreen.kt
- app/src/main/java/com/example/baseaiproject/SessionSecurityScreen.kt
- scripts/validate-compile.ps1

Constraints
- Keep the UI minimal and neutral
- Keep `AppAuthState` as the single app-shell source of truth for this placeholder phase
- Use small explicit callbacks for authenticated destination changes rather than implicit local screen state
- Keep the authenticated destination switching tiny and compatible with later navigation work
- Preserve the existing home greeting output and existing placeholder copy unless small neutral action labels are needed to exercise the protected surfaces
- Verify with the smallest meaningful production Kotlin compile target unless the block expands into test-source changes

What not to change
- Do not implement real login, logout, session restoration, or token refresh behavior yet
- Do not add Navigation Compose routes, back-stack behavior, repositories, or ViewModels yet
- Do not change the greeting formatter output or expand the placeholder screens into broader product behavior
- Do not add new tests unless the destination-switching shape creates a focused need

Done criteria
- The local authenticated state can switch between home, sensitive, and session/security via explicit callbacks
- `AppRoot.kt` continues to render from `AppAuthState` while keeping the protected destination selection centralized and small
- The home surface still renders the existing greeting text unchanged
- The app still compiles and the existing home greeting text remains unchanged on the home surface
- `handoff/validation-report.md` records the actual validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: direct
- Rationale: The next step is another small app-shell wiring pass that should expose all current protected placeholders without adding real navigation or session mechanics.
