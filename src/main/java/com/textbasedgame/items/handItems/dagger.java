package com.textbasedgame.items.handItems;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import com.textbasedgame.items.item;
import com.textbasedgame.items.genericItems.holdables;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;


public class dagger extends holdables{

    int damageToAdd;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Dagger", "Strength", "Agility", "Sharp", "Slicing", "Stabbing"));

    public dagger(){
        setIsAttackingItem(true);
        damageToAdd = (int)(quality * 1.3);
        buffs.add(new pair<>(buffTypes.INTELLIGENCE, 2*quality));
        setPrice(15);
        setName("Dagger");
        setDMGType("Strength");
    }
    public dagger(int qual){
        setQuality(qual);
        setIsAttackingItem(true);
        damageToAdd = (int)(quality * 1.3);
        buffs.add(new pair<>(buffTypes.AGILITY, 2*qual));
        setPrice(15);
        setName("Dagger");
        setDMGType("Strength");
    }

    public int getStatIncrease(){
        return damageToAdd;
    }

    public String getAttackString() {
        return "stab";
    }
    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = dagger.class.getName();
    } 
}