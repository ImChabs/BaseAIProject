## Phase Context
- Active phase: Phase 2 - Token lifecycle behavior
- Phase objective: Grow the local app shell from basic authenticated routing into explicit token lifecycle states that can later support expiration, refresh, and forced-logout behavior without losing the small learning-focused flow.
- Phase slice status: Phase 2 is underway. The app shell now tracks session status separately from protected destination, but the session model still only distinguishes checking, unauthenticated, and authenticated states.

Next block name
BLOCK 015 - Introduce Explicit Local Session Lifecycle States

Objective
Expand the local app-shell session model beyond a single generic authenticated state so the app can represent placeholder lifecycle stages such as active session, access expired, refresh in progress, and unrecoverable session loss while keeping protected destination routing separate.

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
- Keep protected destination routing separate from session lifecycle state
- Prefer explicit state modeling over boolean combinations
- Keep the block small and directly useful for later real session and token work
- Verify with the smallest meaningful production Kotlin compile target unless the block expands into test-source changes

What not to change
- Do not implement real login, logout persistence, session restoration, token refresh, network calls, or forced logout rules yet
- Do not add Navigation Compose routes, back-stack behavior, repositories, or ViewModels yet
- Do not change the greeting formatter output or expand the placeholder screens into broader product behavior
- Do not add new tests unless the state split creates a focused need

Done criteria
- The local app-shell model can represent more than one post-login session lifecycle stage without re-coupling destination state
- `MainActivity.kt` and `AppRoot.kt` still keep the local app shell centralized and easy to follow
- The placeholder UI surfaces the added session lifecycle states clearly enough for learning and later iteration
- Existing protected destination switching remains intact
- The home surface still renders the existing greeting text unchanged when the session is active
- The app still compiles
- `handoff/validation-report.md` records the actual validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: direct
- Rationale: The session/destination split is complete, so the next step is a specific follow-on state-model expansion that should remain small and locally verifiable.
