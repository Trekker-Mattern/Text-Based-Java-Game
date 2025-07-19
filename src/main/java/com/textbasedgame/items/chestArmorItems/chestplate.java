package com.textbasedgame.items.chestArmorItems;
import java.util.Set;

import com.textbasedgame.items.genericItems.chestArmor;

import java.util.HashSet;
import java.util.Arrays;

public class chestplate extends chestArmor{
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Chestplate", "Metal", "Heavy Armor", "Heavy"));
    public chestplate(){
        armorAdd = quality * 2;
        setPrice(45);
        setName("Chestplate");

    }
    public chestplate(int qual){
        setQuality(qual);
        armorAdd = quality * 2;
        setPrice(45);
        setName("Chestplate");

    }
    public void onEquip(){
        
    }
    public void onUnequip(){

    }
    protected Set<String> getTagsSet() {

        return tags;
    }
    
}