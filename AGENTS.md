# AGENTS.md

## Stack
- Native Android app
- Kotlin
- Jetpack Compose
- Material 3
- AndroidX Navigation Compose
- AndroidX ViewModel
- Kotlin Coroutines and Flow
- Gradle Kotlin DSL

Update this section if the repository stack changes in a durable way.

## Project Identity
- This repository is a small Android **Session Demo** for practicing authenticated session handling.
- Optimize for learning value, clarity, and correctness over product breadth.
- Keep naming, copy, and flows neutral unless the blueprint explicitly changes direction.
- Prefer a compact and realistic implementation rather than a feature-rich demo.
- When in doubt, choose the smaller solution that better reinforces session/auth concepts.

## Instruction Ownership
- `AGENTS.md` defines durable repository rules: architecture, structure, naming, state handling, auth/session handling expectations, dependency/design constraints, and verification expectations.
- `docs/blueprint.md` defines the current product goals, scope, roadmap, scenarios, and out-of-scope boundaries.
- `handoff/next-block.md` defines the immediate next implementation block when the block workflow is in use.
- `handoff/validation-report.md` is the live validation artifact for the current block when the validation workflow is in use.
- `handoff-history/` is archival only and should not be treated as the live source of truth unless historical lookup is actually needed.
- `docs/official-docs.md` is a selective reference index for official framework and library guidance when behavior is uncertain.
- `.agents/skills/` contains repository-local workflow skills. Treat them as operational guidance, not as a replacement for `AGENTS.md`.
- `.codex/` contains optional repository-local agent configuration.

## Planning Inputs
- Read only the files needed for the current task.
- For product goals, auth/session scope, roadmap context, and overall app direction, consult `docs/blueprint.md` if present.
- For the immediate next implementation step, consult `handoff/next-block.md` if present.
- For the current block validation state, consult `handoff/validation-report.md` if present.
- When framework or library behavior is uncertain, prefer official documentation and consult `docs/official-docs.md` if present.
- Use `handoff-history/` only for archival or historical lookup when it is actually relevant.
- Do not treat old handoffs or archived notes as permission to expand scope beyond the current blueprint.

## Core Project Principles
- Treat authenticated session behavior as the central concern of this repository.
- Keep the app intentionally small and easy to reason about.
- Prefer explicit and testable auth/session states over implicit behavior.
- Keep protected access behavior centralized and consistent.
- Do not pass tokens manually from screen to screen.
- Distinguish clearly between:
  - unauthenticated state
  - authenticated state
  - expired access token
  - refresh attempt in progress
  - unrecoverable session failure
  - no-internet / transport failures
- Treat refresh failure as a session-ending event unless the blueprint explicitly changes that rule.
- Avoid adding unrelated product/domain features that dilute auth/session learning.

## Project Structure
- Organize code primarily by feature.
- Use `core` only for truly shared or cross-cutting concerns.
- For this repository, typical shared areas may include:
  - `core/navigation`
  - `core/session`
  - `core/network`
  - `core/storage`
  - `core/common`
- Prefer feature folders for app-facing flows such as:
  - `feature/auth`
  - `feature/home`
  - `feature/sensitive`
  - `feature/sessionsecurity`
- A feature may contain `presentation`, `domain`, `data`, and `navigation` when that separation adds real clarity.
- Very small features may start simpler, but structure should remain compatible with later auth/session growth.

## Layer Rules
- `presentation`: screens, roots, viewmodels, UI state, UI actions, UI events, screen-specific orchestration.
- `domain`: domain models, use cases, repository contracts, session rules, auth-related business decisions that should stay independent of UI and storage details.
- `data`: repository implementations, local/remote data sources, DTOs, persistence entities, mappers, token/session storage integrations, network integrations.
- `navigation`: route definitions and navigation wiring when navigation complexity justifies a dedicated area.
- `domain` must not depend on `data` or `presentation`.
- `presentation` consumes `domain`.
- `data` implements `domain` contracts.
- Keep session/token storage and refresh mechanics out of composables.
- UI must observe session state; it must not own session truth.

## Auth And Session Architecture Rules
- Maintain one centralized source of truth for session/auth state.
- Prefer a dedicated session manager/store/repository boundary over scattered token handling.
- Access token and refresh token responsibilities must remain distinct in code and naming.
- Protected requests should obtain auth context centrally, not via ad hoc parameter plumbing.
- Unauthorized responses, revoked sessions, expired tokens, and network failures must not be collapsed into one generic error path.
- Manual logout and forced logout must converge through a consistent session-clearing path.
- Session restoration on app launch must be modeled explicitly, not inferred indirectly from UI navigation alone.
- If token expiry metadata exists, keep it explicit and testable.
- Never implement session continuation through hidden assumptions such as “user is still active in the UI, so session remains valid”.

## Sensitive Data Rules
- Never log access tokens, refresh tokens, raw credentials, authorization headers, or full session payloads.
- Do not expose sensitive values in UI, previews, sample data, analytics, or error messages.
- Store only the minimum session data needed by the app.
- Prefer secure storage patterns when actual secret persistence is implemented.
- Keep debug/demo observability focused on state meaning, not raw secret values.
- Redact or omit sensitive values in test fixtures when possible.

## Network And Failure Handling
- Keep auth-related network behavior centralized.
- If networking is introduced, prefer one consistent mechanism for attaching auth and handling auth failures.
- Handle no-internet / transport failures separately from authentication failures.
- Do not treat every failed request as proof that the session is invalid.
- Only clear session automatically when the failure semantics truly indicate unrecoverable auth loss, according to the project rules.
- Refresh behavior should be controlled, bounded, and observable in state.
- Avoid retry loops or hidden refresh recursion.

## Screen Conventions
- If a screen needs a ViewModel or orchestration layer, prefer paired `<Screen>Root` and `<Screen>Screen` composables.
- Root naming should follow `<Screen>Root`.
- Keep `Screen` and `Root` in the same file unless the file becomes hard to maintain.
- Typical structure for a complex screen:
  - `FeatureScreen.kt`
  - `FeatureViewModel.kt`
  - `FeatureState.kt`
  - `FeatureAction.kt`
  - `FeatureEvent.kt`
- `State` should usually be a `data class`.
- `Action` and `Event` should usually be `sealed interface` types when multiple variants are needed.
- Previews belong to the presentational `Screen`.
- `Root` should not have a preview.
- Very simple screens may omit the root when no ViewModel or orchestration layer is needed.

## Navigation Conventions
- Keep navigation small and explicit.
- The main app flow should clearly separate unauthenticated and authenticated destinations.
- Prefer route names that reflect user-facing intent, not implementation detail.
- For this project, keep route structure aligned with the small auth demo shape unless the blueprint expands it:
  - splash/session-check
  - login
  - home
  - sensitive
  - session/security
- Navigation should react to centralized auth state changes consistently across the app.

## State Management
- Prefer immutable UI state.
- Use `StateFlow` for long-lived UI state when a ViewModel is present.
- Use `SharedFlow` or an equivalent one-off event mechanism for transient events.
- Prefer state hoisting in Compose.
- Presentational screens should receive state plus explicit callbacks or actions.
- Keep meaningful business logic out of composables.
- Avoid representing auth/session lifecycle with fragile boolean combinations when a sealed model or explicit state type is clearer.
- Make loading, recovery, expired-session, and forced-logout states explicit where they affect UX.

## Use Cases
- Use cases are optional, not mandatory.
- Introduce them when they add real value through business logic, orchestration, reuse, clarity, or testability.
- Prefer use cases for important auth/session behaviors when they improve readability or isolation, for example:
  - login
  - restore session
  - validate session
  - refresh session
  - logout
  - handle unauthorized result
- Do not add trivial pass-through use cases that only delegate to a repository.

## Naming Rules
- Prefer clear auth/session terminology:
  - `Session`
  - `AuthState`
  - `AccessToken`
  - `RefreshToken`
  - `SessionStatus`
  - `SessionRepository`
- Avoid vague names such as `Manager`, `Helper`, `Util`, or `Handler` unless their responsibility is genuinely broad and cross-cutting.
- Name failures according to meaning where possible, such as unauthorized, revoked, expired, network unavailable, refresh failed.
- Keep names neutral and didactic; avoid unnecessary business branding in this repository.

## Dependency And Design Rules
- Prefer official, stable Android libraries and the Kotlin standard library first.
- Do not add dependencies without a clear need.
- Avoid unnecessary wrappers and abstractions.
- Prefer the right solution over the superficially smallest diff.
- Do not weaken architecture just to minimize diff size.
- Avoid overengineering.
- Do not touch unrelated files unless necessary for the task.
- Do not introduce large-framework complexity or backend-style patterns that add more ceremony than learning value.
- If a dependency is only for demo polish and not for auth/session learning, it is probably out of scope.

## UI And UX Rules
- Keep UI minimal, readable, and secondary to behavior correctness.
- Prefer clarity over polish.
- Session-related states should be understandable from the UI.
- The session/security area should help explain behavior, not become a dashboard product.
- Avoid decorative complexity that does not reinforce the learning goal.

## Testing And Verification Focus
- Prioritize tests and validation around auth/session risk.
- Valuable targets include:
  - session restoration decisions
  - refresh success/failure behavior
  - forced logout behavior
  - differentiation between auth failures and connectivity failures
  - protected-state transitions
- Add tests when they provide real value.
- Do not add ceremonial tests.
- Choose the smallest test type that covers the real risk.

## Block Workflow Expectations
- When the repository is operating in block mode, complete exactly one coherent block per chat or execution unless the user explicitly asks for something else.
- A block should be one coherent, reviewable, independently verifiable unit of work with one primary objective and the smallest meaningful validation for that scope.
- Treat phases as bounded planning slices rather than open-ended buckets.
- Target roughly 4 to 8 implementation blocks for one phase slice.
- A single phase slice must not continue past 10 implementation blocks without an explicit phase re-slice.
- If the remaining scope is likely to exceed 10 blocks, re-plan before continuing:
  - merge adjacent low-risk blocks only when the merged result is still reviewable and independently verifiable
  - otherwise split the remaining scope into a new phase slice
- Keep the live handoff concise, specific, and actionable.
- Treat `handoff/next-block.md` as the live next step and `handoff-history/` as append-only history.
- Next-block handoffs must include an `Execution Recommendation` section with both `Recommended reasoning effort` and `Recommended execution mode` guidance.
- Keep `handoff/validation-report.md` aligned with what was actually validated.
- If a future block is ambiguous, prefer the smaller and more session-focused interpretation.

## Verification And Testing
- Always try to verify changes before considering the task complete.
- Use the smallest meaningful verification for the affected scope.
- Prefer focused module-level or target-level verification over full project builds.
- Repo defaults for this single-module Android base:
  - PowerShell/Windows compile: `.\scripts\validate-compile.ps1`
  - Bash/WSL compile: `bash scripts/validate-compile.sh`
  - Default targeted compile task: `:app:compileDebugKotlin`
  - For `androidTest`-only changes, use the same compile validation script with `:app:compileDebugAndroidTestKotlin`
  - PowerShell/Windows unit tests: `.\scripts\validate-unit-tests.ps1`
  - Bash/WSL unit tests: `bash scripts/validate-unit-tests.sh`
  - Default targeted unit-test task: `:app:testDebugUnitTest`
- If the build graph changes later, update these defaults rather than silently assuming they still fit.
- Use targeted instrumentation or Compose UI tests only when UI behavior changes warrant them.
- Avoid `clean` and full rebuilds unless truly necessary.
- Do not leave compile errors caused by the change.
- If verification could not be completed, state it explicitly.

## Maintenance Notes
- Keep this file durable and repository-specific.
- Put current product scope, roadmap, scenarios, and out-of-scope decisions in `docs/blueprint.md`, not here.
- Do not turn this file into a task log.
- Update this file when repository-wide engineering expectations change in a durable way.