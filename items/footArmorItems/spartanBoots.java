package items.footArmorItems;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import items.genericItems.boots;

public class spartanBoots extends boots {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Heavy Armor", "Boots", "Shoes" ,"Legs Armor", "Bronze", "Strength", "Metal"));
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
}
