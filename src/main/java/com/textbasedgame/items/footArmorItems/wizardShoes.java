package com.textbasedgame.items.footArmorItems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.items.chestArmorItems.wizardCloak;
import com.textbasedgame.items.equipables;
import com.textbasedgame.items.genericItems.boots;
import com.textbasedgame.items.headArmorItems.wizardHat;
import com.textbasedgame.items.item;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;

public class wizardShoes extends boots{
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Light Armor", "Boots", "Shoes" , "Cloth", "Intelligence"));
    private static final Set<Class<? extends equipables>> setItems = new HashSet<>(Arrays.asList(wizardShoes.class, wizardHat.class, wizardCloak.class));
    public wizardShoes(){
        armorAdd = (int)(quality * 1.2);
        setPrice(11);
        setName("Slippers");
        equipmentSetName = "Wizard Set";
        buffs.add(new pair<>(buffTypes.INTELLIGENCE, 1));
    }
    public wizardShoes(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.2);
        setPrice(11);
        setName("Slippers");
        buffs.add(new pair<>(buffTypes.INTELLIGENCE, 1));
    } 
    @Override
    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    public Set<Class<? extends equipables>> getSetItems() {
        return setItems;
    }
    public pair<buffTypes, Integer> getSetBuff() {
        return new pair<>(buffTypes.INTELLIGENCE, 5);
    }
    @Override
    public void printInfo() {} 
}
