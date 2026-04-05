## Phase Context
- Active phase: Phase 2 - Token lifecycle behavior
- Phase objective: Grow the local app shell from basic authenticated routing into explicit token lifecycle states that can later support expiration, refresh, and forced-logout behavior without losing the small learning-focused flow.
- Phase slice status: Phase 2 is underway. The local app shell now models active, access-expired, refreshing, and unrecoverable protected-session states separately from the protected destination, but unrecoverable session loss still does not drive a dedicated forced-logout path.

Next block name
BLOCK 016 - Add Local Forced Logout Placeholder Path

Objective
Make the unrecoverable protected-session lifecycle state converge through a consistent local forced-logout path so the placeholder app shell can distinguish manual logout from session-ending auth loss without introducing real token or persistence behavior yet.

Relevant files
- AGENTS.md
- handoff/validation-report.md
- app/src/main/java/com/example/baseaiproject/AppRoot.kt
- app/src/main/java/com/example/baseaiproject/MainActivity.kt
- app/src/main/java/com/example/baseaiproject/LoginScreen.kt
- app/src/main/java/com/example/baseaiproject/HomeScreen.kt
- app/src/main/java/com/example/baseaiproject/SensitiveScreen.kt
- app/src/main/java/com/example/baseaiproject/SessionSecurityScreen.kt
- scripts/validate-compile.ps1

Constraints
- Keep the UI minimal and neutral
- Keep one centralized local app-shell source of truth
- Keep protected destination routing separate from logout reason
- Make manual logout and forced logout converge through the same local session-clearing path
- Keep the block placeholder-only and directly useful for later real auth/session work
- Verify with the smallest meaningful production Kotlin compile target unless the block expands into test-source changes

What not to change
- Do not implement real login, logout persistence, session restoration, token refresh, network calls, or backend-driven unauthorized handling yet
- Do not add Navigation Compose routes, back-stack behavior, repositories, or ViewModels yet
- Do not expand the placeholder screens into broader product behavior or dashboard-style diagnostics
- Do not add new tests unless the forced-logout placeholder flow creates a focused need

Done criteria
- An unrecoverable protected-session state can trigger a clear local forced-logout transition back to login
- Manual logout and forced logout share the same local session-clearing path
- The login surface can distinguish a normal unauthenticated entry from a forced-logout placeholder entry with minimal neutral copy
- Protected destination state still resets predictably when leaving authenticated content
- The app still compiles
- `handoff/validation-report.md` records the actual validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: direct
- Rationale: The next step is a small follow-on behavior pass that should stay local to the current app shell and can be verified with the same targeted compile check.
