package com.textbasedgame.items.footArmorItems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.items.chestArmorItems.wizardCloak;
import com.textbasedgame.items.equipables;
import com.textbasedgame.items.genericItems.boots;
import com.textbasedgame.items.headArmorItems.wizardHat;

import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;

public class wizardShoes extends boots{
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Light Armor", "Boots", "Shoes" , "Cloth", "Intelligence"));
    private static final Set<Class<? extends equipables>> setItems = new HashSet<>(Arrays.asList(wizardShoes.class, wizardHat.class, wizardCloak.class));
    public wizardShoes(){
        armorAdd = (int)(quality * 0.75);
        setPrice(11);
        setName("Slippers");
        equipmentSetName = "Wizard Set";
        buffs.add(new pair<>(buffTypes.INTELLIGENCE, 1));
        setDescription("These shoes are extremely comfortable but certainly provide little in terms of protection considering they are made from cloth. Some magic seems to course through the silky insides though and you feel empowered to find the rest of the cloth clothing.");
    }
    public wizardShoes(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 0.75);
        setPrice(11);
        setName("Slippers");
        buffs.add(new pair<>(buffTypes.INTELLIGENCE, 1));
        setDescription("These shoes are extremely comfortable but certainly provide little in terms of protection considering they are made from cloth. Some magic seems to course through the silky insides though and you feel empowered to find the rest of the cloth clothing.");
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
