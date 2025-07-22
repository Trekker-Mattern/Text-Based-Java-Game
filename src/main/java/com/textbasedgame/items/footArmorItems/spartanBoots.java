package com.textbasedgame.items.footArmorItems;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.items.equipables;
import com.textbasedgame.items.item;
import com.textbasedgame.items.chestArmorItems.spartanBreastplate;
import com.textbasedgame.items.genericItems.boots;
import com.textbasedgame.items.headArmorItems.spartanHelmet;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;

public class spartanBoots extends boots {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Heavy Armor", "Boots", "Shoes" ,"Legs Armor", "Bronze", "Strength", "Metal"));
    private static final Set<Class<? extends equipables>> setItems = new HashSet<>(Arrays.asList(spartanBoots.class, spartanHelmet.class, spartanBreastplate.class));
    public spartanBoots(){
        armorAdd = (int)(quality * 1.2);
        setPrice(12);
        setName("Sandals");
        equipmentSetName = "Spartan Set";
        buffType = buffTypes.STRENGTH;
        buffValue = 1;
    }
    public spartanBoots(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.2);
        setPrice(12);
        setName("Sandals");
        buffType = buffTypes.STRENGTH;
        buffValue = 1;
    } 
    protected Set<String> getTagsSet() {
        return tags;
    }
    public static pair<buffTypes, Integer> getSetBuff() {
        return new pair<buffTypes,Integer>(buffTypes.STRENGTH, 5);
    }
    public Set<Class<? extends equipables>> getSetItems() {
        return setItems;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = spartanBoots.class;
    } 
}
