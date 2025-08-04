package com.textbasedgame.items.chestArmorItems;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.items.equipables;
import com.textbasedgame.items.item;
import com.textbasedgame.items.footArmorItems.wizardShoes;
import com.textbasedgame.items.genericItems.chestArmor;
import com.textbasedgame.items.headArmorItems.wizardHat;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;
public class wizardCloak extends chestArmor {

    private static final Set<String> tags = new HashSet<>(Arrays.asList("Light Armor", "Boots", "Shoes" , "Cloth", "Intelligence"));
    private static final Set<Class<? extends equipables>> setItems = new HashSet<>(Arrays.asList(wizardShoes.class, wizardHat.class, wizardCloak.class));

    public wizardCloak() {
        armorAdd = (int)(quality * 1.2);
        setPrice(15);
        setName("Wizard Cloak");
        equipmentSetName = "Wizard Set";
        buffs.add(new pair<>(buffTypes.INTELLIGENCE, 1));
    }

    public wizardCloak(int qual) {
        setQuality(qual);
        armorAdd = (int)(quality * 1.2);
        equipmentSetName = "Wizard Set";
        setPrice(15);
        setName("Wizard Cloak");
        buffs.add(new pair<>(buffTypes.INTELLIGENCE, 1));
    }
    @Override
    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = wizardCloak.class.getName();
    } 
    @Override
    public Set<Class<? extends equipables>> getSetItems() {
        return setItems;
    }
    @Override
    public pair<buffTypes, Integer> getSetBuff() {
        return new pair<>(buffTypes.INTELLIGENCE, 5);
    }
    @Override
    public void printInfo() {}
    
}
