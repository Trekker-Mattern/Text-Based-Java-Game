package com.textbasedgame.items.handItems;
import java.util.Set;

import com.textbasedgame.items.item;
import com.textbasedgame.items.genericItems.holdables;

import java.util.HashSet;
import java.util.Arrays;

public class club extends holdables {
    private int increase;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Strength", "Blunt", "Club", "Wood"));

    public club(){
        setIsAttackingItem(true);
        increase = (int)(1.7*quality);
        setPrice(30);
        setName("Club");
        setDMGType("Strength");
        
    }
    public club(int qual){
        setQuality(qual);
        setIsAttackingItem(true);
        increase = (int)(1.7*quality);
        setPrice(30);
        setName("Club");
        setDMGType("Strength");

    }
    public int getStatIncrease(){
        return increase;
    }

    public String getAttackString() {
        return "bonk";
    }
    protected Set<String> getTagsSet() {

        return tags;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = club.class;
    } 
}