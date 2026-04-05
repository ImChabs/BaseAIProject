## Phase Context
- Active phase: Phase 3 - Failure-state coverage
- Phase objective: Start separating auth/session failures from connectivity and startup-recovery outcomes so the demo can teach why these cases should not collapse into one generic unauthenticated path.
- Phase slice status: This is the first block of a new local placeholder slice. The app shell now models protected-session expiration, refresh in progress, refresh recovery, and forced logout locally, but startup outcomes and connectivity-specific failures are not yet distinct.

Next block name
BLOCK 018 - Add Local Session Check Outcome Placeholder Branches

Objective
Extend the session-check placeholder so startup can branch locally into distinct outcomes such as default unauthenticated entry, restored authenticated access, expired-but-recoverable access, and a connectivity-related startup issue without adding real storage, networking, or token persistence.

Relevant files
- AGENTS.md
- handoff/validation-report.md
- app/src/main/java/com/example/baseaiproject/AppRoot.kt
- app/src/main/java/com/example/baseaiproject/MainActivity.kt
- app/src/main/java/com/example/baseaiproject/SessionCheckScreen.kt
- app/src/main/java/com/example/baseaiproject/LoginScreen.kt
- scripts/validate-compile.ps1

Constraints
- Keep the UI minimal and neutral
- Keep one centralized local app-shell source of truth
- Model startup outcomes explicitly instead of implying them through a single continue action
- Keep connectivity-related startup failure distinct from forced logout and generic unauthenticated entry
- Keep the block placeholder-only and directly useful for later real session restoration work
- Verify with the smallest meaningful production Kotlin compile target unless the block expands into test-source changes

What not to change
- Do not implement real session persistence, token refresh networking, backend calls, or storage-backed restoration yet
- Do not add Navigation Compose routes, repositories, or ViewModels yet
- Do not broaden the UI into a diagnostics dashboard or add unrelated feature scope
- Do not add new tests unless the startup-outcome placeholder flow creates a focused need

Done criteria
- The session-check placeholder can drive more than one startup outcome locally
- At least one startup path enters authenticated content without going through the login placeholder
- A connectivity-related startup issue is represented separately from forced logout and the default unauthenticated entry
- Existing refresh-outcome and forced-logout behavior still works
- The app still compiles
- `handoff/validation-report.md` records the actual validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: direct
- Rationale: The next step is still a small app-shell placeholder pass, but it starts a new phase slice by making startup outcomes explicit and separately observable.
