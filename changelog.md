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
- Fixed Library Room *DO NOT FORGET BREAKS IN SWITCH STATEMENT*
- Some improved GUI with a new box that seperates new text from old text and it pushes the old text into the top scrolling section and new text is kept seperate.
- Escalibur and new Sword in stone Room added.
- Refactored the get armor set buff method so that it doesnt use weird bullshit and just pulls directly from the item. Im not sure why I had coded it that way originally but it seems much better now.
- Potion OOB error fix
- Create arrayList during initialization in order to prevent nulls in buffs
- Fixed bug in fighting where buffTypes.INTELLIGENCE was twice instead of buffTypes.STRENGTH
- NEW INFORMATION MENU for Items!
- - Displays super well! Im super happy with how it turned out
- Added clear function to GUI to fully clear top section
- Add comments on which set each item is from in inventories or shops - Which Set Part is done!
- - java.lang.ArrayIndexOutOfBoundsException: Index 4 out of bounds for length 4 : Generic Potion.java:21
- Announce when buffs wear off
- Added Picture Support!! - Thank you Lizzy I love you <3
- Fixed a bug with food items that made it possible that they heal for 0. 
- Added a Action Listener for resizing the window to also resize my images. We will see how performance is effected.
- Changed back to Java 1.8 for compilation in order for others to be able to run the program.
- Spell fought (I spelt it faught **WRONG**)
- Fixed freezes when Updating the picture in the GUI
- Added another case to Library Room
- Added Spartan Spear
- Added keyItems
- Added chestKey keyItem
- Added opening of the chest in the chest room using chest key
- Impliment jailer enemy for obtaining keys
- Updated shop to include 6 items that are based on the area that you are in

---

> Trekker you are absolutely mental, stop spelling armor wrongggggg

---

## To Implement

- Recreate same-type damage boost for weapons in a better way — For each tag in common between weapons, add damage boost (maybe a 0.2x multiplier)
- Revamp Monster Creation System with HashMaps per floor; pass them into monster creation function via array of maps/sets
- Add Monster Strengths & Weaknesses
- Add Rogue set where it translates agility to strength and increases crit maybe. Should be cool and have a new set
- New Stuff on each types of rooms
- New Category of Item, Key Items?? -- I could keep this as a consumable item but I think if we make it a new subcategory of item it will work better.
- Enchantments that you can use on an Item to add buffs or special tags. *Be Careful When Overriding Tags its static right now*
- - This will allow for interesting interactions with chests or other rooms.
- - Add more to the shop, 1 key item? and guarenteed Food items. increase to 6 items probably
- Write menu to look at key items
- Soul Weighing room - Gives you a bonus if all your stats are equal, gives smaller bonus if they are close to each other but not equal
- Spike dodging trap that checks agility to give chest rewards
- Breakable wall that you can only bust down with strength to get rewards
- three pronged room that you get to choose your next room.
- make items a pair that contains the amount of items that you have if they are consumables - reduces size of inventory List
- write a toString for the items

## TO FIX URGENTLY

- Make bosses a little weaker or randomize them less
- FIX CTHULU HES VERY BROKEN
- - possible print statement bug?
- - Honestly idk whats going on everything is awful

---

## Monster Balancing Calculation

Currently Set to A random level based on player level and World Level and then individual stats based on that.
STR is too high they die in like 2 hits
Boss armor is also too high
