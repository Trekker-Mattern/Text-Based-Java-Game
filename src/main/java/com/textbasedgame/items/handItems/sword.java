package com.textbasedgame.items.handItems;

import java.util.Set;

import com.textbasedgame.items.item;
import com.textbasedgame.items.genericItems.holdables;

import java.util.HashSet;
import java.util.Arrays;

public class sword extends holdables {

    private int swordIncrease;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Sword", "Strength", "Sharp", "Slicing"));

    public sword(){
        setIsAttackingItem(true);
        swordIncrease = 2*quality;
        setPrice(30);
        setName("Sword");
        setDMGType("Strength");
        
    }
    public sword(int qual){
        setQuality(qual);
        setIsAttackingItem(true);
        swordIncrease = 2*quality;
        setPrice(30);
        setName("Sword");
        setDMGType("Strength");

    }
    @Override
    public int getStatIncrease(){
        return swordIncrease;
    }
    @Override
    public String getAttackString() {
        return "slice";
    }
    @Override
    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = sword.class.getName();
    } 
}