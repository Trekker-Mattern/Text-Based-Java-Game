package com.textbasedgame.items.handItems;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.items.genericItems.*;
import com.textbasedgame.playerFiles.player.buffTypes;

public class hydraHead extends holdables {
    public static final Set<String> tags = new HashSet<>(Arrays.asList("Intelligence", "Boss Item", "Ranged", "Flame", "Impaling", "Magic"));
    private int attkDMG;

    public hydraHead(){
        setIsAttackingItem(true);
        buffValue = 3;
        buffType = buffTypes.INTELLIGENCE;
        setDMGType("Intelligence");
        setName("Hydra Head");
        setPrice(100);
        setQuality(6);
        attkDMG = 2*quality;
    }

    
    public int getStatIncrease() {
        return attkDMG;
    }

    public String getAttackString(){
        return "breathe fire";
    }

    protected Set<String> getTagsSet() {
        return tags;
    }
}
