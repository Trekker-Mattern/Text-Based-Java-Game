package com.textbasedgame.items.footArmorItems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.items.genericItems.boots;


public class clogs extends boots {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Clogs", "Wood", "Shoes", "Boots", "Heavy", "Heavy Armor"));
    public clogs(){

        armorAdd = (int)(quality * 1.25);
        setPrice(5);
        setName("Clogs");
        setDescription("Some truly uncomfortable wooden shoes. It seems to be better than nothing but only slightly. You can see how these would aid you in any special way.");
        
    }
    public clogs(int qual){
        setQuality(qual);
        armorAdd = (int)(this.quality * 1.25);
        setPrice(5);
        setName("Clogs");
        setDescription("Some truly uncomfortable wooden shoes. It seems to be better than nothing but only slightly. You can see how these would aid you in any special way.");

    }
    @Override
    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    public void printInfo() {}
}
