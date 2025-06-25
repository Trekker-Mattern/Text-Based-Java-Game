package items.headArmorItems;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import items.equipables;
import items.chestArmorItems.wizardCloak;
import items.footArmorItems.wizardShoes;
import items.genericItems.headArmor;
import playerFiles.player.buffTypes;
import util.pair;

public class wizardHat extends headArmor {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Light Armor", "Hat", "Head Armor", "Cloth", "Intelligence"));
    private static final Set<Class<? extends equipables>> setItems = new HashSet<>(Arrays.asList(wizardShoes.class, wizardHat.class, wizardCloak.class));

    public wizardHat(){
        armorAdd = (int)(quality * 1.2);
        setPrice(25);
        setName("Wizard Hat");
        equipmentSetName = "Wizard Set";
        buffType = buffTypes.INTELLIGENCE;
        buffValue = 1;
    }
    public wizardHat(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.2);
        equipmentSetName = "Wizard Set";
        setPrice(25);
        setName("Wizard Hat");
        buffType = buffTypes.INTELLIGENCE;
        buffValue = 1;
    } 
    protected Set<String> getTagsSet() {
        return tags;
    }
    public static pair<buffTypes, Integer> getSetBuff() {
        return new pair<buffTypes,Integer>(buffTypes.INTELLIGENCE, 5);
    }
    public Set<Class<? extends equipables>> getSetItems() {
        return setItems;
    }
}
