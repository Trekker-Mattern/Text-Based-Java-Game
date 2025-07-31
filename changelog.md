# Changelog

## Bug Fixes & Improvements

- Armour equip not working — Fixed? — Needs testing — Looks Fixed
- Typing strength in level up not working — Fixed? — Test — added `.toLowerCase()`
- Hydra — Done? — Needs Testing
- Hand Swapping Fixed!
- Fix Empty Items List on Save Files
- Boolean equipped Fix
- Tags on items for unique checks and code consolidation. Implemented as set/dictionary for fast read — 90% done
- Save Shmeckles as part of save file — Done? — Seems Done
- Proper save implementation for equipped items. Add bool for equipped, then make the dash — DONE? — Seems Done
- Hydra Head item on hydra kill — Seems Done and seems to be working
- Shop selling — Done, NEEDS TESTING
- Portal bug with going too far back in areas and reversed Trekker Random Number Gen
- Strength Potion — lasts 10 turns/fights — Done! — Testing seems good!
- Room with portal to random location/town — Implemented — 1/2 tested — Seems To Work?
- Object-oriented Rooms — Seems to work — Maybe?
- Fix set testing: make a shared map containing class of all other items in set. For each item in MAP, check equipped items; if there is not an item that is an instance of class, then return false.
- Wizard clothes that boost INT
- Spartan set that boosts STR
- Consumable item for fights that does damage (Bomb, throwing knife, etc.) — works better now! Maybe add agility to allow multiple items before monster turn
- Recreate Buff things?
- FIX THE SHOP PLEASE — Seems Fixed
- Create run system that brings you back to last shop area

---

> Trekker you are absolutely mental, stop spelling armor wrongggggg

---

## To Implement

- Recreate same-type damage boost for weapons in a better way — For each tag in common between weapons, add damage boost (maybe a 0.2x multiplier)
- Revamp Monster Creation System with HashMaps per floor; pass them into monster creation function via array of maps/sets
- Add Monster Strengths & Weaknesses

---

## Monster Balancing Calculation

world.stageNum + player.playerLevel  // Too Much!!
