package com.textbasedgame.items.footArmorItems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.items.genericItems.boots;
import com.textbasedgame.items.item;

public class clogs extends boots {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Clogs", "Wood", "Shoes", "Boots", "Heavy", "Heavy Armor"));
    public clogs(){

        armorAdd = (int)(quality * .75);
        setPrice(5);
        setName("Clogs");

    }
    public clogs(int qual){
        setQuality(qual);
        armorAdd = (int)(this.quality * .75);
        setPrice(5);
        setName("Clogs");

    }
    @Override
    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = clogs.class.getName();
    } 
    @Override
    public void printInfo() {}
}
