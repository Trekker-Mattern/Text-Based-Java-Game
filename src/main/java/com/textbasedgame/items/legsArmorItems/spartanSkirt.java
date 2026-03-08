package com.textbasedgame.items.legsArmorItems;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import com.textbasedgame.items.equipables;

import com.textbasedgame.items.chestArmorItems.spartanBreastplate;
import com.textbasedgame.items.footArmorItems.spartanBoots;
import com.textbasedgame.items.genericItems.legsArmor;
import com.textbasedgame.items.headArmorItems.spartanHelmet;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;

public class spartanSkirt extends legsArmor {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Light Armor", "Skirt", "Legs Armor", "Bronze", "Strength", "Cloth"));
    private static final Set<Class<? extends equipables>> setItems = new HashSet<>(Arrays.asList(spartanBoots.class, spartanHelmet.class, spartanBreastplate.class, spartanSkirt.class));
    public spartanSkirt(){
        armorAdd = (int)(quality * 1.4);
        setPrice(30);
        setName("Periskelis");
        equipmentSetName = "Spartan Set";
        buffs.add(new pair<>(buffTypes.STRENGTH, 1));
        setDescription("A skirt dating back to ancient greek times. The bronze seal on the right side seems to hold a little extra power. When looking at the seal you cant help but feel that the rest of the Spartan Armor would make you feel extra strong in your journey.");
    }
    public spartanSkirt(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.4);
        setPrice(30);
        setName("Periskelis");
        buffs.add(new pair<>(buffTypes.STRENGTH, 1));
        setDescription("A skirt dating back to ancient greek times. The bronze seal on the right side seems to hold a little extra power. When looking at the seal you cant help but feel that the rest of the Spartan Armor would make you feel extra strong in your journey.");
    } 
    @Override
    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    public pair<buffTypes, Integer> getSetBuff() {
        return new pair<>(buffTypes.STRENGTH, 5);
    }
    @Override
    public Set<Class<? extends equipables>> getSetItems() {
        return setItems;
    }
    @Override
    public void printInfo() {}
}