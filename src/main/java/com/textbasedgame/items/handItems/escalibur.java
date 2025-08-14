package com.textbasedgame.items.handItems;

import java.util.Set;


import com.textbasedgame.items.genericItems.holdables;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;

import java.util.HashSet;
import java.util.Arrays;

public class escalibur extends holdables {

    private int damage;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Sword", "Strength", "Sharp", "Slicing", "Holy", "Magical"));

    public escalibur(){
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
    public void printInfo() {}
}
