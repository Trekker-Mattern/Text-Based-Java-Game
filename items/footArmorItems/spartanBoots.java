package items.footArmorItems;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import items.equipables;
import items.chestArmorItems.spartanBreastplate;
import items.genericItems.boots;
import items.headArmorItems.spartanHelmet;
import util.pair;

public class spartanBoots extends boots {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Heavy Armor", "Boots", "Shoes" ,"Legs Armor", "Bronze", "Strength", "Metal"));
    private static final Set<Class<? extends equipables>> setItems = new HashSet<>(Arrays.asList(spartanBoots.class, spartanHelmet.class, spartanBreastplate.class));
    public spartanBoots(){
        armorAdd = (int)(quality * 1.2);
        setPrice(12);
        setName("Sandals");
        equipmentSetName = "Spartan Set";
        buffType = "Strength";
        buffValue = 1;
    }
    public spartanBoots(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.2);
        setPrice(12);
        setName("Sandals");
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
