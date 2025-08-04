package com.textbasedgame.items.handItems;

import java.util.Set;

import com.textbasedgame.items.item;
import com.textbasedgame.items.genericItems.holdables;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;

import java.util.HashSet;
import java.util.Arrays;

public class escalibur extends holdables {

    private int damage;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Sword", "Strength", "Sharp", "Slicing", "Holy", "Magical"));

    public escalibur(){
        setIsAttackingItem(true);
        damage = (int)(2.2*7);
        setPrice(60);
        setName("Escalibur");
        setDMGType(damageTypes.STRENGTH);
        buffs.add(new pair<>(buffTypes.AGILITY, 2));
        buffs.add(new pair<>(buffTypes.STRENGTH, 1));
        
    }
    public int getItemDamage(){
        return damage;
    }

    public String getAttackString() {
        return "chop with holy light";
    }
    protected Set<String> getTagsSet() {

        return tags;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = sword.class.getName();
    } 
    @Override
    public void printInfo() {}
}
