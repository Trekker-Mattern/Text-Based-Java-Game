package com.textbasedgame.items.handItems;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import com.textbasedgame.items.item;
import com.textbasedgame.items.genericItems.holdables;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;


public class dagger extends holdables{

    private int damage;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Dagger", "Strength", "Agility", "Sharp", "Slicing", "Stabbing"));

    public dagger(){
        damage = (int)(quality * 1.3);
        buffs.add(new pair<>(buffTypes.AGILITY, 2*quality));
        setPrice(15);
        setName("Dagger");
        setDMGType(damageTypes.STRENGTH);
    }
    public dagger(int qual){
        setQuality(qual);
        damage = (int)(quality * 1.3);
        buffs.add(new pair<>(buffTypes.AGILITY, 2*qual));
        setPrice(15);
        setName("Dagger");
        setDMGType(damageTypes.STRENGTH);
    }
    @Override
    public int getItemDamage(){
        return damage;
    }
    @Override
    public String getAttackString() {
        return "stab";
    }
    @Override    
    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    public void printInfo() {}
}