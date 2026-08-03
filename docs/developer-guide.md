# Developer Guide

This project is a mostly static-state Java game: the runtime, player state, world progression, GUI, and persistence all live in singleton-style classes and are coordinated from `runTime.main(...)`.

## Runtime Flow

1. `runTime.startup()` sets `SAVE_FILE_ROOT`, creates or reuses the `save_Files` directory, loads shop data, loads an existing save if one exists, initializes monsters, sets up the Swing UI, and blocks until the title screen is dismissed.
2. `TitleScreen.openTitleScreen()` shows the start screen. The Start button sets `TitleScreen.gameOpened = true` and wakes the waiting startup thread.
3. If the current run is a new save, `runTime.initializePlayer()` creates the player and either lets the user assign starting points or rolls them automatically.
4. `runTime.givePlayerStartingItems()` grants a starter weapon based on the highest starting stat.
5. The main loop saves the game, refreshes the player UI, and dispatches into `world.menu()` forever.

## Critical Architectural Notes

- Player state is stored statically in `player`. There is no instance-per-save model.
- World progression is stored in `world.stageNum` and `world.AREANUM`.
- Save/load behavior depends on the exact text and JSON shapes currently written by `saveFiles`.
- `roomFactory` uses a seeded queue of upcoming rooms. If that queue is not seeded correctly, dungeon progression breaks.
- The GUI uses synchronization between the title screen and the startup thread. If you change that flow, make sure the wait/notify contract still completes.

## Input and Console Commands

The game accepts both menu input and a few direct commands in the GUI text field. The current console command list is documented in [consoleCommands.md](consoleCommands.md).

Important direct commands handled by the GUI layer include:

- `-lb` or `list buffs`
- `-srs` or `show real stats`
- `-c` or `clear`

## Where To Be Careful

- Do not change save-field order in `saveFiles.save()` unless you also update `saveFiles.readPlayerSave(...)`.
- Do not assume the save folder is preserved between writes; `saveFiles.save()` rebuilds it.
- Do not remove the room queue seeding logic unless you replace it with another deterministic progression model.
- Do not change GUI input handling without checking both `enterButtonListener` and `gui.setInput(...)`.

## Design Quirks And Oddities

- The game is intentionally built around static singleton-style state, so most systems are not instance-driven. That makes the code simple to follow, but also means a lot of behavior is implicitly shared.
- `saveFiles.save()` deletes and recreates the entire save directory on each save. This is unusual, but it keeps the written files aligned with the current session state.
- The world advances in chunks of five stages. Every fifth stage acts like a village checkpoint, and the dungeon flow resumes after that.
- Boss encounters are forced at stage 9 of a ten-room queue cycle, so some difficulty spikes are deliberate rather than random.
- Combat damage is strongly shaped by weapon tags, equipment set bonuses, buffs, and dual-wield interactions. A weapon can matter more for its tags than for its raw damage number.
- Consumables stack in inventory and can be consumed as a group. If two consumables share a class and name, they are treated as the same stack.
- The GUI has a built-in command layer separate from normal story input. Commands like `-lb`, `-srs`, and `-c` work even while the player is otherwise entering narrative choices.
- Some text and naming choices in the code reflect the project’s long-running development history. A few comments, labels, and item names are intentionally informal and may look inconsistent.
- Several balance notes are still open in [changelog.md](changelog.md), especially around bosses, monster strength, and room variety.

## Suggested Change Process

When modifying gameplay behavior, update the relevant method docs in [critical-functions.md](critical-functions.md) and the serialization notes in [save-format.md](save-format.md). Those files are the canonical developer references for the game loop and data layout.

## Practical Editing Guidance

- If you change item constructors, tags, or equality behavior, verify that save/load and inventory stacking still work.
- If you add a new monster type, make sure it is reachable from the monster pools and that its level, speed, and damage numbers fit the current world stage.
- If you change room generation, confirm that the room queue can still be consumed indefinitely without becoming empty.
- If you add a new console command or GUI shortcut, document it here and in [consoleCommands.md](consoleCommands.md) so the input surface stays discoverable.
- If you touch the static state model, check for hidden dependencies in `player`, `world`, `saveFiles`, and `GameProgressWrapper` before splitting anything into instances.
- If a change affects balance rather than behavior, record it in [changelog.md](changelog.md) so future maintenance can separate intended tuning from accidental regressions.