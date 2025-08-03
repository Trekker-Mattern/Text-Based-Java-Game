package com.textbasedgame.items.consumableItems;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumables;
import com.textbasedgame.items.item;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.util.triple;

import java.util.ArrayList;

public class genericPotion extends consumables {
    private static String[] nameStrArr = {"Arcane", "Enchanted", "Ethereal", "Valiant", "Cursed", "Radiant", "Shadowy", "Ancient", "Mystic", "Legendary", "Spectral", "Draconic", "Sacred", "Eldritch", "Bewitched", "Heroic", "Infernal", "Celestial", "Primal", "Noble", "Fabled", "Ghostly", "Runic", "Gleaming", "Whispering","Runed", "Shimmering", "Dire", "Savage", "Obsidian", "Rugged", "Divine", "Corrupted", "Lunar", "Molten", "Astral", "Frostbound", "Venomous", "Gilded", "Ancient-Blooded", "Serpentine", "Phantom", "Thorned", "Blighted", "Sunforged", "Hallowed", "Glowing", "Verdant", "Crystalline", "Vengeful"};
    private static String[] nameArrPt2 = {"Blade", "Tome", "Crown", "Gauntlet", "Runestone", "Chalice", "Grimoire", "Warden", "Phantom", "Knight", "Dragon", "Wraith", "Portal", "Scepter", "Amulet", "Basilisk", "Golem", "Sigil", "Wand", "Crypt", "Sanctum", "Titan", "Shade", "Oracle", "Champion","Beast", "Aegis", "Bard", "Relic", "Glyph", "Throne", "Ember", "Fang", "Seer", "Oath", "Dagger", "Vessel", "Covenant", "Fury", "Guardian", "Scourge", "Maelstrom", "Ruin", "Sentinel", "Scroll", "Spire", "Serpent", "Obelisk", "Chasm", "Enclave"};
    private ArrayList<triple<buffTypes, Integer, Integer>> buffs = new ArrayList<>();
    
    public genericPotion(){
        setPrice(TrekkerMath.randomInt(30, 3));
        String nameStr = nameStrArr[TrekkerMath.randomInt(nameStrArr.length-1, 0)] + " Potion of the " + nameArrPt2[TrekkerMath.randomInt(nameArrPt2.length-1, 0)]; 
        setName(nameStr);
        buffTypes bType = buffTypes.values()[TrekkerMath.randomInt(buffTypes.values().length-1, 0)];
        int bStr = (int)player.luck + TrekkerMath.randomInt(6, 1) - 6;
        int bduration = (int)player.luck + TrekkerMath.randomInt(13, 1);
        buffs.add(new triple<>(bType, bStr, bduration));
    }
    public genericPotion(buffTypes bType, int bStr, int bduration){
        
        buffs.add(new triple<>(bType, bStr, bduration));

        setPrice(15);
        String nameStr = nameStrArr[TrekkerMath.randomInt(nameStrArr.length-1, 0)] + " Potion of the " + nameArrPt2[TrekkerMath.randomInt(nameArrPt2.length-1, 0)]; 
        setName(nameStr);
    }
    public genericPotion(String name, buffTypes bType, int bStr, int bduration){

        buffs.add(new triple<>(bType, bStr, bduration));
        setPrice(15);
        setName(name);
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = genericPotion.class.getName();
    } 
    public void addBuff(buffTypes bType, int bStr, int bduration){
        buffs.add(new triple<>(bType, bStr, bduration));
    }

    public void Use(){
        removeFromInv();
        for(triple<buffTypes, Integer, Integer> t : buffs){
            player.applyBuff(t.first, t.second, t.third);
            gui.printOnGameSide("You get " + t.second + " " + t.first.toString() + " for " + t.third + " turns");
        }
    }
}
