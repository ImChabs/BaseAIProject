## Phase Context
- Active phase: Phase 1 - Session foundation
- Phase objective: Establish a small app shell and screen structure that can grow into the blueprint's splash, login, and protected-home flow while keeping auth transitions explicit before real session mechanics are introduced.
- Phase slice status: In progress. The placeholder app now moves from session check to login to authenticated content and can switch locally across all current protected surfaces, but it still lacks an explicit authenticated exit path back to login.

Next block name
BLOCK 013 - Local Placeholder Logout Path

Objective
Add a minimal explicit logout path from the authenticated placeholder shell back to login so the app can leave authenticated state through one centralized callback without introducing real session storage, logout use cases, or navigation.

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
- Route logout through one explicit app-shell callback rather than screen-owned auth state
- Preserve the current authenticated destination switching behavior
- Keep the change small and compatible with later real session-clearing work
- Verify with the smallest meaningful production Kotlin compile target unless the block expands into test-source changes

What not to change
- Do not implement real login, logout persistence, session restoration, token refresh, or forced logout rules yet
- Do not add Navigation Compose routes, back-stack behavior, repositories, or ViewModels yet
- Do not change the greeting formatter output or expand the placeholder screens into broader product behavior
- Do not add new tests unless the logout wiring creates a focused need

Done criteria
- The authenticated placeholder flow can return to login through an explicit logout callback
- `MainActivity.kt` and `AppRoot.kt` keep the authenticated exit path centralized and small
- Existing authenticated destination switching still works after the logout path is added
- The home surface still renders the existing greeting text unchanged
- The app still compiles
- `handoff/validation-report.md` records the actual validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: direct
- Rationale: The next step is another small app-shell wiring pass that should add a consistent authenticated exit path without widening scope into real session behavior.
