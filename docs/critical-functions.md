# Critical Functions Reference

This document summarizes the functions that control startup, persistence, progression, combat, world generation, and the GUI.

## Table Of Contents

- [Startup And Runtime](#startup-and-runtime)
- [Persistence](#persistence)
- [World Progression](#world-progression)
- [Room Generation](#room-generation)
- [Player Progression And Combat](#player-progression-and-combat)
- [Monsters](#monsters)
- [Items](#items)
- [GUI And Entry Screens](#gui-and-entry-screens)
- [Quirks And Caveats](#quirks-and-caveats)
- [Practical Editing Guidance](#practical-editing-guidance)

## Startup And Runtime

| Function | File | Responsibility | Notes |
| --- | --- | --- | --- |
| `runTime.main(String[])` | `src/main/java/com/textbasedgame/runTime.java` | Starts the game, runs startup, initializes a new player when needed, and executes the main loop. | The loop saves on every iteration, so any save/load bug will surface immediately. |
| `runTime.startup()` | `src/main/java/com/textbasedgame/runTime.java` | Sets the save root, loads data, builds the GUI, and waits for the title screen to be opened. | This is the earliest place where save-folder creation and title-screen synchronization happen. |
| `runTime.initializePlayer()` | `src/main/java/com/textbasedgame/runTime.java` | Creates the player, asks for a name, and assigns skill points. | Writes game-progress data after character creation. |
| `runTime.givePlayerStartingItems()` | `src/main/java/com/textbasedgame/runTime.java` | Grants a starter item based on the dominant starting stat. | Strength favors a club, intelligence favors a wand, agility favors a dagger plus throwing knife. |

## Persistence

| Function | File | Responsibility | Notes |
| --- | --- | --- | --- |
| `saveFiles.readSave()` | `src/main/java/com/textbasedgame/util/saveFiles.java` | Detects new saves and loads existing ones. | Empty or missing save files are treated as a new game. |
| `saveFiles.save()` | `src/main/java/com/textbasedgame/util/saveFiles.java` | Writes the text save, inventory JSON, and game-progress JSON. | It deletes and recreates the save folder before writing. |
| `saveFiles.readPlayerSave(File)` | `src/main/java/com/textbasedgame/util/saveFiles.java` | Restores player stats, inventory, key items, world stage, and progress flags. | The parser depends on the current line order and token layout of the save file. |
| `saveFiles.saveGameProgressJSON()` | `src/main/java/com/textbasedgame/util/saveFiles.java` | Persists the shared story progression flags. | If `GameProgressWrapper.gameProgress` is null, a new `GameProgress` object is written instead. |
| `saveFiles.getItemToAddToInv(Class<? extends item>)` | `src/main/java/com/textbasedgame/util/saveFiles.java` | Instantiates an item from its class. | Falls back to `bread` if reflection fails. |

## World Progression

| Function | File | Responsibility | Notes |
| --- | --- | --- | --- |
| `world.menu()` | `src/main/java/com/textbasedgame/world/world.java` | Chooses between the village menu and the dungeon flow. | Village content appears every fifth stage. |
| `world.villageMenu()` | `src/main/java/com/textbasedgame/world/world.java` | Presents shop, dungeon, items, potions, save, and quit options. | It also routes to item info and key-item display. |
| `world.openDungeon()` | `src/main/java/com/textbasedgame/world/world.java` | Advances into the next room and increments stage progression. | Area changes occur when crossing a multiple-of-five boundary. |
| `world.itemMenu()` | `src/main/java/com/textbasedgame/world/world.java` | Shows inventory and lets the player use or inspect items. | Returns a boolean that indicates whether an item was used successfully. |
| `world.run()` | `src/main/java/com/textbasedgame/world/world.java` | Sends the player back to the last safe area. | This is the escape/reset-style flow used when fleeing. |
| `world.changeArea()` | `src/main/java/com/textbasedgame/world/world.java` | Updates the current area, refreshes monsters, and prints the transition text. | Called when entering a new region during dungeon progression. |
| `world.updateArea()` | `src/main/java/com/textbasedgame/world/world.java` | Recomputes the active area and refreshes monster data. | Used after loading and respawning. |

## Room Generation

| Function | File | Responsibility | Notes |
| --- | --- | --- | --- |
| `roomFactory.setSeed(int)` | `src/main/java/com/textbasedgame/world/roomFactory.java` | Initializes deterministic room generation. | This must be called before dungeon rooms are consumed. |
| `roomFactory.getNextRoom()` | `src/main/java/com/textbasedgame/world/roomFactory.java` | Returns the current room and appends a new future room. | Throws if the queue is empty. |
| `roomFactory.regenerateRoomQueue()` | `src/main/java/com/textbasedgame/world/roomFactory.java` | Rebuilds the room queue from the current seeded random source. | Used after death and other reset-style flows. |
| `roomFactory.getRandomRoom(Random)` | `src/main/java/com/textbasedgame/world/roomFactory.java` | Chooses either a monster room or a special room. | Stage 9 forces a boss room. |
| `roomFactory.getWeightedRoomClass(Random)` | `src/main/java/com/textbasedgame/world/roomFactory.java` | Applies the tier weighting for special rooms. | Current weighting is tier 0 = 40%, tier 1 = 30%, tier 2 = 20%, tier 3 = 10%. |

## Player Progression And Combat

| Function | File | Responsibility | Notes |
| --- | --- | --- | --- |
| `player.allocateSkillPoints(int, int, int)` | `src/main/java/com/textbasedgame/playerFiles/player.java` | Applies starting skill values and recalculates health. | Used for loaded saves and manual allocation. |
| `player.allocateSkillPoints()` | `src/main/java/com/textbasedgame/playerFiles/player.java` | Randomly assigns the starting points. | Used when the player does not choose manual allocation. |
| `player.playerPointAllocation()` | `src/main/java/com/textbasedgame/playerFiles/player.java` | Walks the player through manual point allocation. | Validates the remaining pool before accepting each allocation. |
| `player.addItemToPlayer(item)` | `src/main/java/com/textbasedgame/playerFiles/player.java` | Adds items and stacks consumables when possible. | This is the common entrypoint for inventory insertion. |
| `player.addItemToPlayer(equipables, boolean)` | `src/main/java/com/textbasedgame/playerFiles/player.java` | Adds equipment and optionally equips it immediately. | Used by save-load and item acquisition flows. |
| `player.getArmorSetBuff()` | `src/main/java/com/textbasedgame/playerFiles/player.java` | Computes set bonuses from equipped armor. | Returns null when no full set is active. |
| `player.fightMonster(monster)` | `src/main/java/com/textbasedgame/playerFiles/player.java` | Resolves a round of combat between the player and a monster. | Chooses turn order from speed, applies damage, and updates the GUI. |
| `player.damageDone()` | `src/main/java/com/textbasedgame/playerFiles/player.java` | Calculates the player’s outgoing damage. | Uses weapon damage, stats, crit logic, and same-type weapon synergy. |
| `player.damageTaken(monster)` | `src/main/java/com/textbasedgame/playerFiles/player.java` | Calculates incoming monster damage after armor and dodge logic. | Calls death handling if damage would kill the player. |
| `player.gainXP(int)` | `src/main/java/com/textbasedgame/playerFiles/player.java` | Adds XP and triggers level-up checks. | Can level multiple times if enough XP is earned. |
| `player.death()` | `src/main/java/com/textbasedgame/playerFiles/player.java` | Handles death, prints final stats, saves, and resets the room queue. | `player.death(monster, int)` is the combat-specific version. |
| `player.onRespawn()` | `src/main/java/com/textbasedgame/playerFiles/player.java` | Restores the player after a full death reset. | Clears buffs, resets world position, and restores health. |

## Monsters

| Function | File | Responsibility | Notes |
| --- | --- | --- | --- |
| `monsterCreator.createMonster()` | `src/main/java/com/textbasedgame/monsters/monsterCreator.java` | Creates the next regular monster from the active monster pool. | Uses reflection to instantiate the selected monster type. |
| `monsterCreator.createBoss()` | `src/main/java/com/textbasedgame/monsters/monsterCreator.java` | Creates the current boss monster. | Uses the boss monster pool instead of the regular monster pool. |
| `monsterCreator.createMonsterLevel()` | `src/main/java/com/textbasedgame/monsters/monsterCreator.java` | Calculates the base monster level from the current world stage. | Stage progression is the main driver of difficulty scaling. |
| `monsterCreator.fastMonsterSpeed(int)` | `src/main/java/com/textbasedgame/monsters/monsterCreator.java` | Generates a higher-speed monster stat. | Speed scales from the supplied monster level. |
| `monsterCreator.medMonsterSpeed(int)` | `src/main/java/com/textbasedgame/monsters/monsterCreator.java` | Generates a medium-speed monster stat. | Used by balanced monster variants. |
| `monsterCreator.slowMonsterSpeed(int)` | `src/main/java/com/textbasedgame/monsters/monsterCreator.java` | Generates a lower-speed monster stat. | Used by slower, heavier enemies. |
| `monsterCreator.strongMonsterStr(int)` | `src/main/java/com/textbasedgame/monsters/monsterCreator.java` | Generates a high-strength monster stat. | Strength scales from the supplied monster level. |
| `monsterCreator.weakMonsterStr(int)` | `src/main/java/com/textbasedgame/monsters/monsterCreator.java` | Generates a lower-strength monster stat. | Used for fragile enemies. |
| `monsterCreator.medMonsterStr(int)` | `src/main/java/com/textbasedgame/monsters/monsterCreator.java` | Generates a medium-strength monster stat. | Used by balanced monster variants. |
| `monsterCreator.strongMonsterHealth(int)` | `src/main/java/com/textbasedgame/monsters/monsterCreator.java` | Generates a high-health monster stat. | Currently scales directly from the monster level. |
| `monsterCreator.weakMonsterHealth(int)` | `src/main/java/com/textbasedgame/monsters/monsterCreator.java` | Generates a lower-health monster stat. | Used for fragile enemies. |
| `monsterCreator.medMonsterHealth(int)` | `src/main/java/com/textbasedgame/monsters/monsterCreator.java` | Generates a medium-health monster stat. | Uses a lower multiplier than the strong variant. |
| `monsterCreator.getBossArmor(int)` | `src/main/java/com/textbasedgame/monsters/monsterCreator.java` | Determines boss armor from the monster level. | Boss armor stays low until high levels. |
| `monster.subtractHealth(int)` | `src/main/java/com/textbasedgame/monsters/monster.java` | Applies incoming damage to a monster. | Checks weapon weaknesses before armor reduction. |
| `monster.getDamageAfterWeaknessCheck(int)` | `src/main/java/com/textbasedgame/monsters/monster.java` | Adjusts damage when the player’s weapons match monster weaknesses. | Both hands are checked independently. |
| `monster.printMonster()` | `src/main/java/com/textbasedgame/monsters/monster.java` | Pushes monster UI and combat text to the GUI. | Also exposes debug output when the player name is `debug`. |

## Items

| Function | File | Responsibility | Notes |
| --- | --- | --- | --- |
| `item.getItemName()` | `src/main/java/com/textbasedgame/items/item.java` | Returns the item display name. | Used throughout menus and save output. |
| `item.getPrice()` | `src/main/java/com/textbasedgame/items/item.java` | Returns the item price. | Pricing is stored on the base item class. |
| `item.getDescription()` | `src/main/java/com/textbasedgame/items/item.java` | Returns the item description text. | Used by item-info display flows. |
| `item.Use()` | `src/main/java/com/textbasedgame/items/item.java` | Base item interaction hook. | Subclasses override this for actual behavior. |
| `item.printInfo()` | `src/main/java/com/textbasedgame/items/item.java` | Prints the item’s detailed information. | Required by the base item contract. |
| `item.equals(item)` | `src/main/java/com/textbasedgame/items/item.java` | Compares item value and identity. | Used for save/load consistency and duplicate checks. |
| `consumables.Use()` | `src/main/java/com/textbasedgame/items/consumables.java` | Decrements stack count and removes the item when empty. | All stackable consumables inherit this behavior. |
| `consumables.increaseStackValue(int)` | `src/main/java/com/textbasedgame/items/consumables.java` | Adds more copies to the current stack. | Used when duplicate consumables are picked up. |
| `consumables.inventoryPrintingString()` | `src/main/java/com/textbasedgame/items/consumables.java` | Formats stackable items for inventory display. | Prefixes the stack count in the inventory menu. |
| `consumables.equals(consumables)` | `src/main/java/com/textbasedgame/items/consumables.java` | Compares stackable consumables by class and name. | Used to merge duplicate stacks. |
| `equipables.Use()` | `src/main/java/com/textbasedgame/items/equipables.java` | Toggles an equipment item on or off. | Equipping updates player slots, buffs, and GUI state. |
| `equipables.equipToSlot()` | `src/main/java/com/textbasedgame/items/equipables.java` | Places the item into its equipment slot. | Implemented by specific equipment subclasses. |
| `equipables.getItemTags()` | `src/main/java/com/textbasedgame/items/equipables.java` | Returns the item’s tag set. | Tags drive synergy, weaknesses, and weapon interaction logic. |
| `equipables.getSetBuff()` | `src/main/java/com/textbasedgame/items/equipables.java` | Returns the equipment-set bonus. | Default implementation returns no bonus. |
| `equipables.getSetItems()` | `src/main/java/com/textbasedgame/items/equipables.java` | Returns the set members needed for a full set bonus. | Used by `player.getArmorSetBuff()`. |

### Item Behavior Notes

- Consumables are stackable, and duplicates are merged when added to inventory.
- Equipment toggles between equipped and unequipped states through `Use()`.
- Equipment stats, tags, and set bonuses affect combat through `player.getStrength()`, `player.getAgility()`, `player.getIntelligence()`, and `player.getArmor()`.
- Item class identity matters for save/load. If you add a new item type or change its constructors, update the deserialization path accordingly.

## Quirks And Caveats

- The game stores most state in static fields. That simplifies the current architecture, but it means helper methods can have side effects that persist across the entire run.
- The save system rewrites the full save folder during `saveFiles.save()`. Any future save extension needs to preserve that behavior or deliberately replace it.
- `world.menu()` routes between village and dungeon play based on `stageNum % 5`, so stage progression is a core gameplay switch rather than just a counter.
- `roomFactory` maintains a queue of future rooms. The queue is generated from a seed, so room order is reproducible for a given run unless the seed is changed.
- Monster weaknesses are checked against the player’s equipped weapon tags. In practice, tags can be as important as raw item stats.
- Consumable items are merged by class and name, so two items that look different in source but share those values may stack unexpectedly.
- Several systems still carry balance caveats from active development, including boss tuning, monster scaling, and room weighting. See [changelog.md](changelog.md) for current known issues.

## GUI And Entry Screens

| Function | File | Responsibility | Notes |
| --- | --- | --- | --- |
| `gui.setupGui()` | `src/main/java/com/textbasedgame/GUI/gui.java` | Builds the main Swing frame and installs the input controls. | Also sets the F11 fullscreen toggle. |
| `gui.runGui()` | `src/main/java/com/textbasedgame/GUI/gui.java` | Builds the inventory, text, image, and input panels. | Called when the start button is pressed. |
| `gui.setInput(String)` | `src/main/java/com/textbasedgame/GUI/gui.java` | Wakes the game thread with the latest input value. | This is the bridge from Swing events to game logic. |
| `TitleScreen.openTitleScreen()` | `src/main/java/com/textbasedgame/GUI/TitleScreen.java` | Displays the title screen and starts the main UI on demand. | The Start button also releases the startup wait. |
| `enterButtonListener.actionPerformed(ActionEvent)` | `src/main/java/com/textbasedgame/GUI/enterButtonListener.java` | Captures text field input and routes built-in commands. | Handles quit, buff listing, raw-stat display, and clear-top-text before sending input to the backend. |
| `buttonStyler.styleEnterButton(JButton)` | `src/main/java/com/textbasedgame/GUI/Styles/buttonStyler.java` | Styles the main in-game input button. | Uses a custom rounded border and hover handling. |
| `buttonStyler.styleTitleScreenButton(JButton, boolean)` | `src/main/java/com/textbasedgame/GUI/Styles/buttonStyler.java` | Styles the title screen buttons. | Same rounded-button approach, with larger typography. |

## Practical Editing Guidance

- If you change a function that writes to disk, update the save-format reference as part of the same change.
- If you change turn order, combat damage, or stat math, update the player-combat sections here.
- If you add a new top-level UI flow, document where it is entered and which thread owns it.
- If you change item constructors, tags, or equality behavior, verify that save/load and inventory stacking still work.
- If you add a new monster type, make sure it is reachable from `monsterArrayList` and that its level, speed, and damage numbers are sensible for the current world stage.
- If you change room generation, confirm that `roomFactory.getNextRoom()` can still run forever without emptying the queue.
- If you add a new console command or GUI shortcut, document it in both the GUI notes and the console command docs so the input surface stays discoverable.
- If you touch the static state model, check for hidden dependencies in `player`, `world`, `saveFiles`, and `GameProgressWrapper` before splitting anything into instances.
- If a change affects balance rather than behavior, record it in [changelog.md](changelog.md) so future maintenance can separate intended tuning from accidental regressions.
