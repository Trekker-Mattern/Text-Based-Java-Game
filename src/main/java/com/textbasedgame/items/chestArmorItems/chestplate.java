package com.textbasedgame.items.chestArmorItems;
import java.util.Set;

import com.textbasedgame.items.genericItems.chestArmor;

import java.util.HashSet;
import java.util.Arrays;

public class chestplate extends chestArmor{
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Chestplate", "Metal", "Heavy Armor", "Heavy"));
    public chestplate(){
        armorAdd = (int)(quality * 2.4);
        setPrice(45);
        setName("Chestplate");
        setDescription("Your everyday run-of-the-mill chestplate. It provides substancial protection due to its heavy metal makeup, but it doesnt seem to have any special properties.");
    }
    public chestplate(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 2.4);
        setPrice(45);
        setName("Chestplate");
        setDescription("Your everyday run-of-the-mill chestplate. It provides substancial protection due to its heavy metal makeup, but it doesnt seem to have any special properties.");
        
    } 
    public void onEquip(){
        
    }
    public void onUnequip(){

    }
    protected Set<String> getTagsSet() {

        return tags;
    }
    @Override
    public void printInfo() {}
    
}