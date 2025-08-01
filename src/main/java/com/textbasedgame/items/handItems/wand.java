package com.textbasedgame.items.handItems;

import java.util.Set;

import com.textbasedgame.items.item;
import com.textbasedgame.items.genericItems.holdables;

import java.util.HashSet;
import java.util.Arrays;

public class wand extends holdables {
    private int wandIncrease;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Intelligence", "Staff", "Ranged", "Magic"));

    public wand(){
        setIsAttackingItem(true);
        wandIncrease = 2*quality;
        setPrice(10);
        setName("Wand");
        setDMGType("Intelligence");
    }
    public wand(int qual){
        setQuality(qual);
        setIsAttackingItem(true);
        wandIncrease = 2*quality;
        setPrice(10);
        setName("Wand");
        setDMGType("Intelligence");  
    }
    @Override
    public int getStatIncrease(){
        return wandIncrease;
    }
    @Override
    public String getAttackString() {
        return "cast a spell on";
    }
    @Override
    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = wand.class.getName();
    } 
}
