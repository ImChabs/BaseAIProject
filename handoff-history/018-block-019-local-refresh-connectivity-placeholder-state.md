## Phase Context
- Active phase: Phase 3 - Failure-state coverage
- Phase objective: Start separating auth/session failures from connectivity and startup-recovery outcomes so the demo can teach why these cases should not collapse into one generic unauthenticated path.
- Phase slice status: This is the second block of the current local placeholder slice. Startup session-check outcomes are now explicit, but refresh-time connectivity still collapses into the same path as unrecoverable auth loss.

Next block name
BLOCK 019 - Add Local Refresh Connectivity Placeholder State

Objective
Extend the authenticated placeholder flow so a refresh attempt can fail because connectivity is unavailable while keeping the user in a distinct authenticated recovery-visible state instead of forcing the same logout path used for unrecoverable auth failure.

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
- Keep connectivity-related refresh interruption distinct from forced logout and unrecoverable auth loss
- Keep the block placeholder-only and directly useful for later real network-aware refresh handling
- Preserve the startup outcome branches added in the previous block
- Verify with the smallest meaningful production Kotlin compile target unless the block expands into test-source changes

What not to change
- Do not implement real session persistence, token refresh networking, backend calls, or storage-backed restoration yet
- Do not add Navigation Compose routes, repositories, or ViewModels yet
- Do not broaden the UI into a diagnostics dashboard or add unrelated feature scope
- Do not add new tests unless the new connectivity-specific refresh path creates a focused need

Done criteria
- The session/security placeholder can represent a refresh attempt blocked by connectivity
- The connectivity-blocked refresh path stays authenticated and does not reuse the forced-logout clearing path
- The UI exposes that connectivity-related state distinctly from unrecoverable auth loss
- Existing startup outcome branches and forced-logout behavior still work
- The app still compiles
- `handoff/validation-report.md` records the actual validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: direct
- Rationale: The next smallest Phase 3 gap is a bounded app-shell placeholder change that separates refresh-time connectivity loss from actual session-ending auth failure.
