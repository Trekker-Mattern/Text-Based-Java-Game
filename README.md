# **Trekkers Text Based Java Game!!**

- Welcome to Trekkers Text Based Java RPG!

This is a text based game fully written in Java with minimal outside help from sources such as ChatGPT and online sources.
This project is meant to be a learning experience as I write a game from scratch and all of the growing pains that go with it.
This project is completely written in Java. All creative content belongs to the owner and creator.

> Created and Designed by Trekker Mattern

## Summary Created by Microsoft Copilot

Overview
Trekker's Game is a turn-based, text-and-GUI-driven fantasy adventure where players explore a sprawling world of themed rooms, battle diverse monsters, collect and equip powerful items, and solve puzzles to progress. The game blends classic RPG mechanics—leveling up strength and intelligence, managing inventory and currency—with modern conveniences like a scrollable text interface, picture support, and a robust save/load system.

Implemented Features

- Equipment & Buff System
- Items carry tags for quick, unique checks; equipment state is saved and reloaded correctly.
- Armor sets (Wizard and Spartan) grant set-based boosts directly from item properties.
- Consumables include strength potions lasting ten turns, bombs, throwing knives, and food items that reliably heal.
- Buff tracking announces when temporary bonuses wear off.
- Exploration & Rooms
- Object-oriented room framework powers library chambers (with multiple cases), portal rooms that whisk players to random towns, and a breakable-wall puzzle unlocked by strength.
- Chest rooms require finding and using a chest key; a jailer enemy guards key items.
- A run system returns players to the last visited shop area.
- GUI & Presentation
- Dual-pane text interface separates new messages from history, with a clear function to reset the active pane.
- New information menu displays item details in a dedicated panel.
- Picture support integrates artwork alongside text, with a resize listener to adjust images when the window changes dimensions.
- Economy & Saving
- Shmeckles currency is saved alongside inventory and equipped items.
- Shops now stock six items tailored to each area, and players can sell gear back to vendors.
- Content & Encounters
- Hydra boss fight drops a unique Hydra Head item upon victory.
- Special weapons and rooms added, including Escalibur in a Sword-in-the-Stone scenario and the Spartan Spear.
- Hydra, jails, and library puzzles enrich the world; monster creation uses a random level tied to player and world level (ready for balance tuning).
