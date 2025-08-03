package com.textbasedgame.items.handItems;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.items.genericItems.holdables;
import com.textbasedgame.items.item;

public class club extends holdables {
    private final int damage;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Strength", "Blunt", "Club", "Wood"));

    public club(){
        setIsAttackingItem(true);
        damage = (int)(1.7*quality);
        setPrice(30);
        setName("Club");
        setDMGType(damageTypes.STRENGTH);
        
    }
    public club(int qual){
        setQuality(qual);
        setIsAttackingItem(true);
        damage = (int)(1.7*quality);
        setPrice(30);
        setName("Club");
        setDMGType(damageTypes.STRENGTH);

    }
    @Override
    public int getItemDamage() {
        return damage;
    }
    @Override
    public String getAttackString() {
        return "bonk";
    }
    @Override
    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = club.class.getName();
    } 
}