package items.footArmorItems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import items.equipables;
import items.chestArmorItems.wizardCloak;
import items.genericItems.boots;
import items.headArmorItems.wizardHat;
import util.pair;

public class wizardShoes extends boots{
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Light Armor", "Boots", "Shoes" , "Cloth", "Intelligence"));
    private static final Set<Class<? extends equipables>> setItems = new HashSet<>(Arrays.asList(wizardShoes.class, wizardHat.class, wizardCloak.class));
    public wizardShoes(){
        armorAdd = (int)(quality * 1.2);
        setPrice(11);
        setName("Slippers");
        equipmentSetName = "Wizard Set";
        buffType = "Intelligence";
        buffValue = 1;
    }
    public wizardShoes(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.2);
        setPrice(11);
        setName("Slippers");
        buffType = "Intelligence";
        buffValue = 1;
    } 
    protected Set<String> getTagsSet() {
        return tags;
    }
    public Set<Class<? extends equipables>> getSetItems() {
        return setItems;
    }
    public static pair<String, Integer> getSetBuff() {
        return new pair<String,Integer>("Intelligence", 5);
    }
}
