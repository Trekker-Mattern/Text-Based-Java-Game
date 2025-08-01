package com.textbasedgame.items.chestArmorItems;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.items.equipables;
import com.textbasedgame.items.item;
import com.textbasedgame.items.footArmorItems.spartanBoots;
import com.textbasedgame.items.genericItems.chestArmor;
import com.textbasedgame.items.headArmorItems.spartanHelmet;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;

public class spartanBreastplate extends chestArmor {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Heavy Armor", "Chestplate", "Legs Armor", "Bronze", "Strength", "Metal"));
    private static final Set<Class<? extends equipables>> setItems = new HashSet<>(Arrays.asList(spartanBoots.class, spartanHelmet.class, spartanBreastplate.class));
    public spartanBreastplate(){
        armorAdd = (int)(quality * 2.2);
        setPrice(52);
        setName("Spartan Breastplate");
        equipmentSetName = "Spartan Set";
        buffs.add(new pair<>(buffTypes.STRENGTH, 1));
    }
    public spartanBreastplate(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 2.2);
        setPrice(52);
        setName("Spartan Breastplate");
        buffs.add(new pair<>(buffTypes.STRENGTH, 1));
    } 
    @Override
    protected Set<String> getTagsSet() {

        return tags;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = spartanBreastplate.class.getName();
    } 
    @Override
    public pair<buffTypes, Integer> getSetBuff() {
        return new pair<>(buffTypes.STRENGTH, 5);
    }
    @Override
    public Set<Class<? extends equipables>> getSetItems() {
        return setItems;
    }
}
