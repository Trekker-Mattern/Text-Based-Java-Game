package com.textbasedgame.items.handItems;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.items.item;
import com.textbasedgame.items.genericItems.*;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;

public class hydraHead extends holdables {
    public static final Set<String> tags = new HashSet<>(Arrays.asList("Intelligence", "Boss Item", "Ranged", "Flame", "Impaling", "Magic"));
    private int damage;

    public hydraHead(){
        setIsAttackingItem(true);
        buffs.add(new pair<>(buffTypes.INTELLIGENCE, 3));
        setDMGType(damageTypes.INTELLIGENCE);
        setName("Hydra Head");
        setPrice(100);
        setQuality(6);
        damage = 2*quality;
    }

    
    public int getItemDamage() {
        return damage;
    }

    public String getAttackString(){
        return "breathe fire";
    }

    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = hydraHead.class.getName();
    } 
    @Override
    public void printInfo() {}
}
