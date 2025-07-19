package com.textbasedgame.items.footArmorItems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.items.equipables;
import com.textbasedgame.items.chestArmorItems.wizardCloak;
import com.textbasedgame.items.genericItems.boots;
import com.textbasedgame.items.headArmorItems.wizardHat;
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
        buffType = buffTypes.INTELLIGENCE;
        buffValue = 1;
    }
    public wizardShoes(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.2);
        setPrice(11);
        setName("Slippers");
        buffType = buffTypes.INTELLIGENCE;
        buffValue = 1;
    } 
    protected Set<String> getTagsSet() {
        return tags;
    }
    public Set<Class<? extends equipables>> getSetItems() {
        return setItems;
    }
    public static pair<buffTypes, Integer> getSetBuff() {
        return new pair<buffTypes,Integer>(buffTypes.INTELLIGENCE, 5);
    }
}
