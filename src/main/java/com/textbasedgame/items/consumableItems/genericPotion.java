package com.textbasedgame.items.consumableItems;
import com.textbasedgame.items.consumables;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.TrekkerMath;
public class genericPotion extends consumables {
    private static String[] nameStrArr = {"Arcane", "Enchanted", "Ethereal", "Valiant", "Cursed", "Radiant", "Shadowy", "Ancient", "Mystic", "Legendary", "Spectral", "Draconic", "Sacred", "Eldritch", "Bewitched", "Heroic", "Infernal", "Celestial", "Primal", "Noble", "Fabled", "Ghostly", "Runic", "Gleaming", "Whispering","Runed", "Shimmering", "Dire", "Savage", "Obsidian", "Rugged", "Divine", "Corrupted", "Lunar", "Molten", "Astral", "Frostbound", "Venomous", "Gilded", "Ancient-Blooded", "Serpentine", "Phantom", "Thorned", "Blighted", "Sunforged", "Hallowed", "Glowing", "Verdant", "Crystalline", "Vengeful"};
    private static String[] nameArrPt2 = {"Blade", "Tome", "Crown", "Gauntlet", "Runestone", "Chalice", "Grimoire", "Warden", "Phantom", "Knight", "Dragon", "Wraith", "Portal", "Scepter", "Amulet", "Basilisk", "Golem", "Sigil", "Wand", "Crypt", "Sanctum", "Titan", "Shade", "Oracle", "Champion","Beast", "Aegis", "Bard", "Relic", "Glyph", "Throne", "Ember", "Fang", "Seer", "Oath", "Dagger", "Vessel", "Covenant", "Fury", "Guardian", "Scourge", "Maelstrom", "Ruin", "Sentinel", "Scroll", "Spire", "Serpent", "Obelisk", "Chasm", "Enclave"};
    private buffTypes bType;
    private int bStr;
    private int bduration;
    
    public genericPotion(){
        setPrice(TrekkerMath.randomInt(30, 3));
        String nameStr = nameStrArr[TrekkerMath.randomInt(nameStrArr.length, 0)] + " Potion of the " + nameArrPt2[TrekkerMath.randomInt(nameArrPt2.length, 0)]; 
        setName(nameStr);
        bType = buffTypes.values()[TrekkerMath.randomInt(buffTypes.values().length, 0)];
        bStr = (int)player.luck + TrekkerMath.randomInt(6, 1);
        bduration = (int)player.luck + TrekkerMath.randomInt(13, 1) - 6;
    }
    public genericPotion(buffTypes bType, int bStr, int bduration){
        this.bType = bType;
        this.bStr = bStr;
        this.bduration = bduration;

        setPrice(15);
        String nameStr = nameStrArr[TrekkerMath.randomInt(nameStrArr.length, 0)] + " Potion of the " + nameArrPt2[TrekkerMath.randomInt(nameArrPt2.length, 0)]; 
        setName(nameStr);
    }
    public genericPotion(String name, buffTypes bType, int bStr, int bduration){
        this.bType = bType;
        this.bStr = bStr;
        this.bduration = bduration;

        setPrice(15);
        setName(name);
    }

    public void Use(){
        removeFromInv();
        player.applyBuff(bType, bStr, bduration);
        
    }
}
