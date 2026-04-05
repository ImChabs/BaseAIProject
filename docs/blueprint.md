# Project Blueprint

## Purpose

This project is a simple Android practice app built to learn and validate how authenticated sessions are handled in modern mobile applications.

Its purpose is not to create a large or production-ready product, but to build a small and focused **Session Demo** that helps practice the full client-side lifecycle of authentication: login, session persistence, protected access, token expiration, refresh behavior, failure handling, and logout scenarios.

Keep this document project-specific. `AGENTS.md` holds durable repository rules; this blueprint holds the current product direction for this repository instance.

## Product Direction

Optimize for a small, didactic, and realistic Android app that models the most important auth/session behaviors used in production-style apps without adding unnecessary business complexity.

For this project:
- keep the app intentionally small
- prefer neutral naming and neutral copy
- focus on session lifecycle correctness over feature breadth
- make auth behavior visible and understandable for learning purposes
- avoid adding unrelated product scope that weakens the main learning goal

## Core Scope

The current scope is limited to a compact Android app that is sufficient to practice and validate:

- login with a simple credential flow
- centralized authenticated session state
- session restoration on app launch
- protected screens and protected requests
- access token expiration handling
- refresh token handling
- automatic refresh attempts for expired access
- forced logout when refresh fails or the session becomes invalid
- handling of unauthorized states, revoked sessions, and no-internet scenarios
- safe handling of sensitive auth/session information

This is a real feature scope, but intentionally narrow and focused on auth/session learning.

## Initial Scope Or MVP

The first meaningful version of this project should include:

- a splash or startup session-check flow
- a login screen
- a protected home screen
- a sensitive screen or action behind authenticated state
- a session/security screen for observing session behavior
- access token handling
- refresh token handling
- clear expiration behavior
- session restoration after app restart
- automatic refresh attempt after protected request failure
- forced logout when session recovery fails
- handling for no-internet cases during session validation or refresh
- safe behavior that avoids exposing tokens or sensitive session data

The MVP should remain small in UI and business logic, but serious in how it models session behavior.

## Priorities

1. Correct session lifecycle behavior
2. Clear and centralized auth state
3. Accurate handling of access token vs refresh token responsibilities
4. Small scope with strong learning value
5. Reliable and understandable failure-state handling
6. Clean extension path for later auth-related improvements

## Out Of Scope For Now

- social login or OAuth provider integration
- browser-based authorization flows
- biometric login as a primary sign-in method
- complex business/domain features
- large dashboard or multi-feature product behavior
- multi-module expansion unless clearly needed later
- advanced UI polish beyond what supports clarity
- backend complexity beyond what is required to practice session flows

Biometric reauthentication may be added later if the project is expanded, but it is not part of the base MVP.

## Technical Direction

- Keep the project focused on modern Android development practices.
- Keep the app small and easy to reason about.
- Model auth as a single session flow with one source of truth.
- Do not pass tokens manually from screen to screen.
- Keep protected request behavior centralized and consistent.
- Treat token expiration, refresh, logout, and invalid-session handling as first-class app behaviors.
- Prefer explicit and testable session states over implicit behavior.
- Avoid patterns that encourage insecure handling of credentials or session data.

## Product Flow

The app should follow a small but realistic auth journey:

1. **App launch**
  - the app checks whether a previous session exists
  - if the session is valid, the user continues into protected content
  - if the access token is expired but refresh is still possible, the app attempts silent recovery
  - if recovery fails, the user is sent to login
  - if connectivity is unavailable, the app responds clearly and predictably

2. **Login**
  - the user signs in with a simple credential flow
  - the app establishes a session
  - protected content becomes available

3. **Protected usage**
  - protected requests use the current session automatically
  - the user should not manually manage auth state during normal valid-session usage

4. **Expiration and refresh**
  - when the access token is no longer valid, the app attempts a controlled refresh
  - if refresh succeeds, the session continues
  - if refresh fails, the session is cleared and the user is forced out

5. **Sensitive access**
  - one screen or action should represent content that is more sensitive than the main home screen
  - this exists to reinforce that some authenticated content needs stricter protection and clearer session awareness

6. **Logout**
  - manual logout clears the session cleanly
  - forced logout happens when the session is no longer recoverable

## Recommended Screens

The app should remain small. The recommended minimum set is:

- **Splash / Session Check**
- **Login**
- **Home (Protected)**
- **Sensitive Screen**
- **Session / Security Screen**

The session/security screen is especially useful in this project because it makes auth/session behavior visible and easier to learn from.

## Required Scenarios To Practice

The project should explicitly cover these scenarios:

- successful login
- app relaunch with valid session
- access token expired
- refresh token expired
- refresh token invalid or revoked
- protected request returns unauthorized
- app starts without internet
- refresh attempt without internet
- manual logout
- forced logout after unrecoverable auth failure
- safe handling of sensitive data in app behavior and logs

## Session Behavior Principles

This project should model the following principles clearly:

- the session must come from a centralized source of truth
- access token and refresh token have different responsibilities
- access token expiration does not automatically reset just because the user is active in the UI
- session continuation should happen through valid refresh logic, not hidden assumptions
- unauthorized states and network failures must not be treated as the same problem
- failed refresh is a session-ending event in this demo
- protected content should react consistently to auth state changes across the app

## Roadmap

- Phase 1: Session foundation
  Establish the app shell, startup session check, login entry, protected route structure, and the main session state model.

- Phase 2: Token lifecycle behavior
  Add access token expiration handling, refresh flow behavior, protected request recovery, and forced logout rules.

- Phase 3: Failure-state coverage
  Add explicit support for revoked session, refresh failure, unauthorized responses, and no-internet scenarios.

- Phase 4: Session visibility and refinement
  Improve the session/security screen, tighten auth behavior clarity, and polish the demo so it works well as a learning project and simple portfolio artifact.

## Block Strategy

Implementation should happen in small blocks. Each block should deepen auth/session correctness without making the app larger than necessary.

Treat roadmap phases as bounded slices, not indefinite buckets:
- plan each phase for roughly 4 to 8 implementation blocks
- do not let one phase slice continue past 10 blocks without an explicit re-slice
- if a phase is likely to exceed that cap, either merge adjacent low-risk work only when the result stays reviewable and independently verifiable, or split the remaining scope into a new phase
- keep detailed block sequencing in `handoff/next-block.md` and archived handoffs, not in this blueprint

This blueprint provides persistent direction across multiple block-based chats:
- `handoff/next-block.md` defines the immediate next block
- this blueprint defines the broader destination and boundaries

If a future block conflicts with this blueprint, prefer the smaller and more session-focused interpretation unless the product direction is intentionally updated.

## Success Criteria

This repository instance is successful if it becomes:

- easy to understand
- realistic enough to teach production-style session handling concepts
- small enough to maintain comfortably
- structured enough to extend in future phases
- useful as a personal practice project and a simple demo artifact

## Maintenance Notes

- Update this document when the product direction changes in a durable way.
- Do not use this file as a task log or archive.
- Keep historical implementation details in handoff artifacts, not here.
- Keep the scope centered on auth/session learning unless the project is intentionally expanded later.