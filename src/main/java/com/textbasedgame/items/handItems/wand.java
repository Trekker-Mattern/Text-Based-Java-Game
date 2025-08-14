package com.textbasedgame.items.handItems;

import java.util.Set;


import com.textbasedgame.items.genericItems.holdables;

import java.util.HashSet;
import java.util.Arrays;

public class wand extends holdables {
    private int damage;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Intelligence", "Staff", "Ranged", "Magic"));

    public wand(){
        damage = 2*quality;
        setPrice(10);
        setName("Wand");
        setDMGType(damageTypes.INTELLIGENCE);
    }
    public wand(int qual){
        setQuality(qual);
        damage = 2*quality;
        setPrice(10);
        setName("Wand");
        setDMGType(damageTypes.INTELLIGENCE);  
    }
    @Override
    public int getItemDamage(){
        return damage;
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
    public void printInfo() {}
}
