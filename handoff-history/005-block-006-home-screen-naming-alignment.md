## Phase Context
- Active phase: Phase 1 - Session foundation
- Phase objective: Establish a small app shell and screen structure that can grow into the blueprint's splash, login, and protected-home flow without adding session mechanics too early.
- Phase slice status: In progress. The current recorded slice has completed validation setup, focused greeting-test cleanup, and greeting screen extraction; the next block should align the placeholder screen naming with the roadmap before broader auth flows are added.

Next block name
BLOCK 006 - Home Screen Naming Alignment

Objective
Rename the extracted placeholder greeting surface to a `HomeScreen` and align the existing focused instrumentation test naming with that screen while keeping the current text and launch behavior unchanged.

Relevant files
- AGENTS.md
- handoff/validation-report.md
- app/src/main/java/com/example/baseaiproject/MainActivity.kt
- app/src/main/java/com/example/baseaiproject/GreetingScreen.kt
- app/src/main/java/com/example/baseaiproject/HomeScreen.kt
- app/src/androidTest/java/com/example/baseaiproject/GreetingInstrumentedTest.kt
- app/src/androidTest/java/com/example/baseaiproject/HomeScreenInstrumentedTest.kt
- scripts/validate-compile.ps1

Constraints
- Keep the current displayed text, preview output, and app launch behavior unchanged
- Limit the block to naming and file-organization alignment for the existing single-screen surface
- Keep the focused instrumentation assertion on the current greeting text rather than expanding UI behavior
- Verify with the smallest meaningful `androidTest` compile target because both production and instrumentation Kotlin sources will change

What not to change
- Do not add navigation, auth state, or protected-route behavior yet
- Do not change the greeting formatter output or introduce new UI copy
- Do not add new tests beyond renaming or updating the existing focused instrumentation test
- Do not expand into broader feature-package restructuring unless a small adjacent fix is required to keep the rename coherent

Done criteria
- `MainActivity.kt` invokes `HomeScreen`
- The extracted screen file and preview use home-oriented naming while preserving the current output
- The focused instrumentation test file and class use home-oriented naming and still assert the current greeting text
- `handoff/validation-report.md` records the actual `androidTest` compile validation run for the block

## Execution Recommendation
- Recommended reasoning effort: medium
- Recommended execution mode: direct
- Rationale: The next block is still bounded, but it coordinates a small production-plus-androidTest rename and should use the narrow `androidTest` compile check.
