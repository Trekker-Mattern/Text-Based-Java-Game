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
        buffType = buffTypes.INTELLIGENCE;
        buffValue = 1;
    }

    public wizardCloak(int qual) {
        setQuality(qual);
        armorAdd = (int)(quality * 1.2);
        equipmentSetName = "Wizard Set";
        setPrice(15);
        setName("Wizard Cloak");
        buffType = buffTypes.INTELLIGENCE;
        buffValue = 1;
    }

    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = wizardCloak.class;
    } 

    public Set<Class<? extends equipables>> getSetItems() {
        return setItems;
    }
    public static pair<buffTypes, Integer> getSetBuff() {
        return new pair<buffTypes,Integer>(buffTypes.INTELLIGENCE, 5);
    }
    
}
