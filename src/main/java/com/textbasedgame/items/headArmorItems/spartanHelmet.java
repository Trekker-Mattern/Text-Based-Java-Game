package com.textbasedgame.items.headArmorItems;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import com.textbasedgame.items.equipables;
import com.textbasedgame.items.item;
import com.textbasedgame.items.chestArmorItems.spartanBreastplate;
import com.textbasedgame.items.footArmorItems.spartanBoots;
import com.textbasedgame.items.genericItems.headArmor;
import com.textbasedgame.items.legsArmorItems.spartanSkirt;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;

public class spartanHelmet extends headArmor {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Heavy Armor", "Helmet", "Head Armor", "Bronze", "Strength", "Metal"));
    private static final Set<Class<? extends equipables>> setItems = new HashSet<>(Arrays.asList(spartanBoots.class, spartanHelmet.class, spartanBreastplate.class, spartanSkirt.class));
    public spartanHelmet(){
        armorAdd = (int)(quality * 1.8);
        setPrice(25);
        setName("Spartan Helmet");
        equipmentSetName = "Spartan Set";
        buffs.add(new pair<>(buffTypes.STRENGTH, 1));
    }
    public spartanHelmet(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.8);
        equipmentSetName = "Spartan Set";
        setPrice(25);
        setName("Spartan Helmet");
        buffs.add(new pair<>(buffTypes.STRENGTH, 1));
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
        classofItem = spartanHelmet.class.getName();
    }
    
}
