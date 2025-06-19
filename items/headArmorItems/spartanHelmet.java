package items.headArmorItems;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import items.equipables;
import items.chestArmorItems.spartanBreastplate;
import items.legsArmorItems.spartanSkirt;
import items.footArmorItems.spartanBoots;
import items.genericItems.headArmor;
import util.pair;

public class spartanHelmet extends headArmor {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Heavy Armor", "Helmet", "Head Armor", "Bronze", "Strength", "Metal"));
    private static final Set<Class<? extends equipables>> setItems = new HashSet<>(Arrays.asList(spartanBoots.class, spartanHelmet.class, spartanBreastplate.class, spartanSkirt.class));
    public spartanHelmet(){
        armorAdd = (int)(quality * 1.8);
        setPrice(25);
        setName("Spartan Helmet");
        equipmentSetName = "Spartan Set";
        buffType = "Strength";
        buffValue = 1;
    }
    public spartanHelmet(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.8);
        equipmentSetName = "Spartan Set";
        setPrice(25);
        setName("Spartan Helmet");
        buffType = "Strength";
        buffValue = 3;
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
