---
name: implement-block-and-produce-handoff
description: Implement one bounded development block in this repository, verify the affected scope, and produce the next-block handoff. Use when working one block per chat or thread and a clean implementation-to-handoff workflow is needed.
---

# Implement Block and Produce Handoff

## Purpose

- Complete exactly one development block per chat or thread.
- Implement only the requested scope, plus small adjacent fixes required to make the block correct and verifiable.
- Keep phases bounded by re-slicing before one phase slice grows past a reviewable block budget.
- Leave a concise handoff for the next block at `handoff/next-block.md`.
- Archive each completed handoff as a separate history file under `handoff-history/`.

## Inputs

- Read `AGENTS.md` first and follow it as the repository source of truth for durable conventions and verification expectations.
- Read `docs/blueprint.md` if it exists when product direction or scope boundaries matter.
- Read `handoff/next-block.md` if it exists and use it as the live source of truth for the immediate next block unless the user explicitly says otherwise.
- Use `handoff-history/` only for archival or historical lookup when it is actually relevant.

## Workflow

1. Read `AGENTS.md`.
2. Read the current user request, `docs/blueprint.md` if it exists, and `handoff/next-block.md` if it exists.
3. Identify the active phase slice before editing.
   - Use the current handoff when it already includes explicit phase context.
   - Otherwise infer the active phase conservatively from `docs/blueprint.md`, the current request, and the immediate handoff scope.
   - Treat phase-budget counting as phase-local context, not as the repo-wide block number or the archive prefix.
4. Define the exact block scope before editing:
   - What must change
   - What can stay untouched
   - What adjacent fix is acceptable only if required to complete the block correctly
5. Implement the block.
6. Verify the smallest meaningful affected scope using the repository rules in `AGENTS.md`.
   - If `.agents/skills/validate-fix-loop/SKILL.md` exists, follow it for the validation/fix loop and update `handoff/validation-report.md`.
7. If verification fails because of block changes, fix issues that remain in scope or are a small required adjacent correction.
   - Rerun the same validation target within the loop limit instead of broadening verification immediately.
8. Summarize the result clearly.
9. Check the active phase slice before generating the next handoff.
   - Target roughly 4 to 8 implementation blocks per phase slice.
   - Never continue the same phase slice past 10 implementation blocks without an explicit re-slice.
   - If the remaining scope is likely to exceed that cap, do not keep splitting indefinitely.
   - Merge adjacent low-risk work only when the merged next block is still reviewable and independently verifiable.
   - Otherwise, make the next handoff the first block of a new phase slice and state that re-slice explicitly.
   - Never generate block 11 for an unchanged phase slice.
10. Overwrite `handoff/next-block.md` with the next-block handoff.
11. Write a second archival copy of that same handoff into `handoff-history/` as a new file without overwriting prior history files.
12. Treat the archive step as incomplete unless it creates exactly one new `handoff-history/` file whose numeric prefix is unique and is the next value after the current highest prefix.

## Verification

- Always attempt verification.
- Follow `AGENTS.md` for verification scope and command selection.
- When available, prefer the repo-local validation scripts through `.agents/skills/validate-fix-loop/SKILL.md`.
- Keep the live validation artifact at `handoff/validation-report.md`.
- Record exactly what was run and whether it passed, failed and was fixed, or could not be completed.

## Handoff Output

- Create the `handoff` directory first if it does not already exist.
- Create the `handoff-history` directory first if it does not already exist.
- Always create or overwrite `handoff/next-block.md`.
- Keep it short, specific, and actionable.
- Include a `Phase Context` section in every generated handoff.
- Include an `Execution Recommendation` section in every generated handoff.
- Treat the skill-defined handoff structure here as the source of truth for generated next-block handoffs.
- Include:
  - `Phase Context`
    - `Active phase`
    - `Phase objective`
    - `Phase slice status`
  - `Next block name`
  - `Objective`
  - `Relevant files`
  - `Constraints`
  - `What not to change`
  - `Done criteria`
- Format the execution recommendation as:
  - `## Execution Recommendation`
  - `- Recommended reasoning effort: <low|medium|high|xhigh>`
  - `- Recommended execution mode: <plan_first|direct>`
  - `- Rationale: <brief explanation>`
- When older handoffs do not include explicit phase context, infer it once from the current repo direction and then add it to the newly generated handoff.
- Base the next block on the current state of the codebase after your changes, not on the original request.

## Archive Rule

- Always write a second archival copy of the generated handoff into `handoff-history/`.
- Never overwrite or delete prior files in `handoff-history/`.
- Use one file per archived handoff, not a cumulative history file.
- Name each archive file as `<archive-sequence>-<block-slug>.md`.
- `archive-sequence` is the zero-padded numeric prefix and must increase from the highest existing prefix in `handoff-history/`.
- The archive step is invalid if the chosen prefix already exists or skips past the next available prefix.
- `block-slug` should describe the block and may include the block number if useful, but the numeric archive sequence is the uniqueness source going forward.
- Example: `026-block-27-feature-filter-bar.md`.
- Every generated handoff must include an `Execution Recommendation` section.
- Allowed values are exactly:
  - `low`
  - `medium`
  - `high`
  - `xhigh`
- Choose the recommendation by evaluating the expected complexity of the next block.
- Use the rubric below as guidance, not as a rigid formula.
- Allowed execution mode values are exactly:
  - `plan_first`
  - `direct`
- Always include a brief one-line rationale.
- Decision rubric:
  - `low`: small, mechanical, low-risk work
  - `medium`: default for normal implementation blocks
  - `high`: meaningful ambiguity, multi-layer coordination, or harder verification
  - `xhigh`: hardest cases with deep coordination, difficult debugging, or major tradeoffs
- Execution mode rubric:
  - `plan_first`: use when the next block has meaningful ambiguity, competing implementations, or needs repo inspection before editing
  - `direct`: use when the next block is already specific, bounded, and ready for implementation
