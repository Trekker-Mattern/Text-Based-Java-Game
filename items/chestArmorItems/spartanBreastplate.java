package items.chestArmorItems;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import items.genericItems.chestArmor;

public class spartanBreastplate extends chestArmor {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Heavy Armor", "Chestplate", "Legs Armor", "Bronze", "Strength", "Metal"));
    public spartanBreastplate(){
        armorAdd = (int)(quality * 2.2);
        setPrice(52);
        setName("Spartan Breastplate");
        equipmentSetName = "Spartan Set";
        buffType = "Strength";
        buffValue = 1;
    }
    public spartanBreastplate(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 2.2);
        setPrice(52);
        setName("Spartan Breastplate");
        buffType = "Strength";
        buffValue = 1;
    } 
    protected Set<String> getTagsSet() {

        return tags;
    }
}
