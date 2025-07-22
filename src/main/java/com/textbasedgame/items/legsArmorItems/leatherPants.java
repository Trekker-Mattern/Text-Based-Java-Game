package com.textbasedgame.items.legsArmorItems;

import java.util.Set;

import com.textbasedgame.items.item;
import com.textbasedgame.items.genericItems.legsArmor;

import java.util.HashSet;
import java.util.Arrays;

public class leatherPants extends legsArmor {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Light Armor", "Pants", "Legs Armor", "Leather"));
    public leatherPants(){

        armorAdd = (int)(quality * 1.275);
        setPrice(13);
        setName("Leather Pants");

    }
    public leatherPants(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.275);
        setPrice(13);
        setName("Leather Pants");

    }
    protected Set<String> getTagsSet() {

        return tags;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = leatherPants.class;
    }

}
