package items.legsArmorItems;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import util.pair;
import items.equipables;
import items.chestArmorItems.spartanBreastplate;
import items.footArmorItems.spartanBoots;
import items.genericItems.legsArmor;
import items.headArmorItems.spartanHelmet;

public class spartanSkirt extends legsArmor {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Light Armor", "Skirt", "Legs Armor", "Bronze", "Strength", "Cloth"));
    private static final Set<Class<? extends equipables>> setItems = new HashSet<>(Arrays.asList(spartanBoots.class, spartanHelmet.class, spartanBreastplate.class, spartanSkirt.class));
    public spartanSkirt(){
        armorAdd = (int)(quality * 1.4);
        setPrice(30);
        setName("Periskelis");
        equipmentSetName = "Spartan Set";
        buffType = "Strength";
        buffValue = 1;
    }
    public spartanSkirt(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.4);
        setPrice(30);
        setName("Periskelis");
        buffType = "Strength";
        buffValue = 1;
    } 
    protected Set<String> getTagsSet() {

        return tags;
    }
    public static pair<String, Integer> getSetBuff() {
        return new pair<String,Integer>("Strength", 5);
    }
    public Set<Class<? extends equipables>> getSetItems() {
        return setItems;
    }
    
}