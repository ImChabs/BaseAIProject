## Phase Context
- Active phase: Phase 1 - Session foundation
- Phase objective: Establish a small app shell and screen structure that can grow into the blueprint's splash, login, and protected-home flow without adding session mechanics too early.
- Phase slice status: In progress. The explicit app auth/session state model is now in place, but the app still needs a tiny state host so startup checking can resolve into the placeholder unauthenticated and authenticated flows.

Next block name
BLOCK 011 - Local App Auth State Host

Objective
Introduce one tiny app-level state host for `AppAuthState` so the app can move from startup checking into unauthenticated and authenticated placeholder states through explicit callbacks, without adding real persistence, repositories, ViewModels, or Navigation Compose yet.

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
- Keep `AppAuthState` as the single app-shell source of truth for this placeholder phase
- Use small explicit callbacks for placeholder transitions rather than implicit local screen state
- Keep any authenticated destination switching tiny and compatible with later navigation work
- Preserve the existing home greeting output and neutral placeholder copy unless a small action label is needed to exercise the flow
- Verify with the smallest meaningful production Kotlin compile target unless the block expands into test-source changes

What not to change
- Do not implement real login, logout, session restoration, or token refresh behavior yet
- Do not add Navigation Compose routes, back-stack behavior, repositories, or ViewModels yet
- Do not change the greeting formatter output or expand the placeholder screens into broader product behavior
- Do not add new tests unless the chosen state-host shape creates a focused need

Done criteria
- `MainActivity.kt` or one similarly small app entry host owns `AppAuthState` locally for the placeholder phase
- `AppRoot.kt` renders from that host state and forwards only explicit callbacks needed for the placeholder auth flow
- The startup-check surface can resolve into the login placeholder, and the login placeholder can reach the authenticated home placeholder without real auth logic
- The app still compiles and the existing home greeting text remains unchanged on the home surface
- `handoff/validation-report.md` records the actual validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: direct
- Rationale: The next step is a small, bounded wiring pass that should make the placeholder auth flow exercisable while preserving the explicit state shape for later session work.
