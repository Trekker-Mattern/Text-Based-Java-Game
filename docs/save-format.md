# Save Format Reference

This project uses two persistent save artifacts plus one story-progress file inside `save_Files/`.

## Files Written On Save

- `saveFile.txt` holds the main player and world state in plain text.
- `playerInventory.JSON` holds inventory content and key items in JSON form.
- `GameProgress.JSON` holds persistent story flags such as unlocked systems and NPC state.

## `saveFile.txt`

The text save is line-based and order-sensitive. `saveFiles.readPlayerSave(...)` expects the fields in the exact sequence below:

1. `Player-Name`
2. `Player-Level`
3. `Player-Current-Health`
4. `Player-Maximum-Health`
5. `Player-Strength`
6. `Player-Agility`
7. `Player-Intelligence`
8. `Player-XP-to-Level-Up`
9. `Player-XP`
10. `World-StageNum`
11. `World-AreaNum`
12. `PlayerCoins`
13. `PlayerLuck`

Important details:

- The loader consumes tokens with a `Scanner`, so the labels and spacing matter.
- If you add, remove, or rename fields, you must update both the writer and the parser.
- `World-StageNum` and `World-AreaNum` control where the player resumes in the world map.

## `playerInventory.JSON`

The inventory JSON is written with two top-level arrays:

- `inventory`
- `keyItems`

Inventory entries are serialized with Gson plus the custom `TJSONDeserializer`, which means item class identity is reconstructed during load.

Loading rules:

- `equipables` are restored with their equipped state preserved.
- Non-equipment items are added directly to the main inventory.
- `keyItems` are restored into the dedicated key-item collection.

Important details:

- If you add a new item type, make sure the deserializer can rebuild it.
- If a constructor signature changes, saved items may fail to load.
- Stackable consumables rely on the item implementation to preserve their stack value.

## `GameProgress.JSON`

The game-progress file is serialized from `GameProgressWrapper.gameProgress`.

Current tracked fields:

- `potionBagUnlocked`
- `lizzyMet`
- `iggyMet`

If `gameProgress` is null, the save routine writes a fresh `GameProgress` object instead.

## Operational Notes

- `saveFiles.save()` currently deletes and recreates the entire `save_Files/` directory before writing.
- A new or empty save file is treated as a new game.
- Save loading depends on the file layout being stable. Treat these file formats as part of the public contract of the game code.

## Common Failure Modes

- Save file parse errors usually mean the line order changed or a field is missing.
- Inventory load issues usually mean a new item class is not supported by the deserializer or its constructor shape changed.
- Missing progress flags usually mean `GameProgressWrapper.gameProgress` was never populated before save.
