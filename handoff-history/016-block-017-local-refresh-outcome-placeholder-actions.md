## Phase Context
- Active phase: Phase 2 - Token lifecycle behavior
- Phase objective: Grow the local app shell from basic authenticated routing into explicit token lifecycle states that can later support expiration, refresh, and forced-logout behavior without losing the small learning-focused flow.
- Phase slice status: Phase 2 is nearing the end of this local placeholder slice. The app shell now separates protected destination, protected session lifecycle, and unauthenticated entry reason, and unrecoverable session loss converges through a dedicated local forced-logout path.

Next block name
BLOCK 017 - Add Local Refresh Outcome Placeholder Actions

Objective
Make the session/security placeholder model explicit refresh outcomes so an access-expired local session can either recover back to active access or end through the existing forced-logout path without introducing real networking, storage, or token logic yet.

Relevant files
- AGENTS.md
- handoff/validation-report.md
- app/src/main/java/com/example/baseaiproject/AppRoot.kt
- app/src/main/java/com/example/baseaiproject/MainActivity.kt
- app/src/main/java/com/example/baseaiproject/SessionSecurityScreen.kt
- app/src/main/java/com/example/baseaiproject/HomeScreen.kt
- app/src/main/java/com/example/baseaiproject/SensitiveScreen.kt
- scripts/validate-compile.ps1

Constraints
- Keep the UI minimal and neutral
- Keep one centralized local app-shell source of truth
- Keep protected destination routing separate from session lifecycle transitions
- Keep refresh success and refresh failure outcomes explicit rather than implied
- Reuse the existing forced-logout clearing path for refresh failure
- Keep the block placeholder-only and directly useful for later real auth/session work
- Verify with the smallest meaningful production Kotlin compile target unless the block expands into test-source changes

What not to change
- Do not implement real login, logout persistence, session restoration, token refresh networking, or backend-driven unauthorized handling yet
- Do not add Navigation Compose routes, back-stack behavior, repositories, or ViewModels yet
- Do not expand the placeholder screens into broader product behavior or dashboard-style diagnostics
- Do not add new tests unless the refresh-outcome placeholder flow creates a focused need

Done criteria
- The session/security placeholder offers a clear local path from access expired into refresh in progress
- A refresh success placeholder path returns the protected session lifecycle to active without leaving authenticated content
- A refresh failure placeholder path ends the session through the existing forced-logout transition back to login
- Protected destination state still resets predictably when the forced-logout path is used
- The app still compiles
- `handoff/validation-report.md` records the actual validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: direct
- Rationale: The next step is a bounded local behavior pass that builds directly on the current placeholder lifecycle model and should verify with the same targeted compile check.
