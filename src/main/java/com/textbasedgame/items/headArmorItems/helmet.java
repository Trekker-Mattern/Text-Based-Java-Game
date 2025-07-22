package com.textbasedgame.items.headArmorItems;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import com.textbasedgame.items.item;
import com.textbasedgame.items.genericItems.headArmor;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;


public class helmet extends headArmor{
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Heavy Armor", "Helmet", "Head Armor", "Metal"));
    
    public helmet(){

        armorAdd = (int)(quality * 1.3);
        setPrice(20);
        setName("Helmet");

    }
    public helmet(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.3);
        setPrice(20);
        setName("Helmet");

    }
    
    protected Set<String> getTagsSet() {

        return tags;
    }

    public static pair<buffTypes, Integer> getSetBuff() {
        return new pair<buffTypes,Integer>(null, null);
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = helmet.class;
    } 
}