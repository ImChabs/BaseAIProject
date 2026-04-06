## Phase Context
- Active phase: Phase 3 - Failure-state coverage
- Phase objective: Start separating auth/session failures from connectivity and startup-recovery outcomes so the demo can teach why these cases should not collapse into one generic unauthenticated path.
- Phase slice status: This is the third block of the current local placeholder slice. Startup and refresh-time connectivity placeholder outcomes are now explicit, but protected-request unauthorized auth loss still collapses into the same generic post-session sign-in message.

Next block name
BLOCK 020 - Add Local Unauthorized Protected Request Placeholder Path

Objective
Extend the authenticated placeholder flow so a protected request can locally simulate an unauthorized auth-loss outcome, route through a distinct session-ending path, and surface a clearer unauthenticated entry reason than the current generic forced-logout copy.

Relevant files
- AGENTS.md
- handoff/validation-report.md
- app/src/main/java/com/example/baseaiproject/AppRoot.kt
- app/src/main/java/com/example/baseaiproject/MainActivity.kt
- app/src/main/java/com/example/baseaiproject/LoginScreen.kt
- app/src/main/java/com/example/baseaiproject/SensitiveScreen.kt
- scripts/validate-compile.ps1

Constraints
- Keep the UI minimal and neutral
- Keep one centralized local app-shell source of truth
- Keep unauthorized auth loss distinct from manual logout and connectivity-related session interruption
- Keep the block placeholder-only and directly useful for later centralized unauthorized handling
- Preserve the startup and refresh-connectivity outcome branches added in previous blocks
- Verify with the smallest meaningful production Kotlin compile target unless the block expands into test-source changes

What not to change
- Do not implement real session persistence, token refresh networking, backend calls, or storage-backed restoration yet
- Do not add Navigation Compose routes, repositories, or ViewModels yet
- Do not broaden the UI into a diagnostics dashboard or add unrelated feature scope
- Do not add new tests unless the unauthorized-specific placeholder path creates a focused need

Done criteria
- A protected placeholder flow can simulate unauthorized auth loss without reusing manual logout semantics
- The resulting unauthenticated entry reason is distinct from the current generic forced-logout message
- Existing startup branches, refresh-connectivity behavior, and manual logout behavior still work
- The app still compiles
- `handoff/validation-report.md` records the actual validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: direct
- Rationale: The next smallest Phase 3 gap is a bounded app-shell placeholder path that makes unauthorized protected-request auth loss explicit without introducing real networking yet.
