## Phase Context
- Active phase: Phase 2 - Token lifecycle behavior
- Phase objective: Start separating session status from protected-screen routing so the app shell can grow into explicit token, recovery, and forced-logout states without losing the small learning-focused flow.
- Phase slice status: New phase slice starting. Phase 1 now covers the placeholder session-check, login entry, protected surfaces, local authenticated destination switching, and explicit logout path, but the app still conflates session status with authenticated destination state.

Next block name
BLOCK 014 - Separate Session Status From Protected Destination

Objective
Refactor the local app-shell state so session status and protected destination are modeled independently, preserving the current placeholder flow while creating clear space for later token lifecycle states such as expired access, refresh in progress, and forced logout.

Relevant files
- AGENTS.md
- handoff/validation-report.md
- app/src/main/java/com/example/baseaiproject/AppRoot.kt
- app/src/main/java/com/example/baseaiproject/MainActivity.kt
- app/src/main/java/com/example/baseaiproject/SessionCheckScreen.kt
- app/src/main/java/com/example/baseaiproject/LoginScreen.kt
- app/src/main/java/com/example/baseaiproject/HomeScreen.kt
- app/src/main/java/com/example/baseaiproject/SensitiveScreen.kt
- app/src/main/java/com/example/baseaiproject/SessionSecurityScreen.kt
- scripts/validate-compile.ps1

Constraints
- Keep the UI minimal and neutral
- Keep one centralized local app-shell source of truth
- Preserve the current placeholder session-check, login, destination-switching, and logout behavior
- Prefer explicit state modeling over boolean combinations
- Keep the refactor small and directly useful for later session lifecycle work
- Verify with the smallest meaningful production Kotlin compile target unless the block expands into test-source changes

What not to change
- Do not implement real login, logout persistence, session restoration, token refresh, expired-token handling, or forced logout rules yet
- Do not add Navigation Compose routes, back-stack behavior, repositories, or ViewModels yet
- Do not change the greeting formatter output or expand the placeholder screens into broader product behavior
- Do not add new tests unless the state split creates a focused need

Done criteria
- Session status and protected destination are no longer represented as one combined authenticated shell state
- `MainActivity.kt` and `AppRoot.kt` still keep the local app shell centralized and easy to follow
- Existing placeholder session-check, login, logout, and authenticated destination switching behavior still works after the refactor
- The home surface still renders the existing greeting text unchanged
- The app still compiles
- `handoff/validation-report.md` records the actual validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: plan_first
- Rationale: The next step is a bounded state-model refactor that should stay small, but it benefits from a quick structure pass before editing so later token lifecycle states fit cleanly.
