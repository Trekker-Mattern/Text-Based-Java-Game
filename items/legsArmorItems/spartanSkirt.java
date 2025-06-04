package items.legsArmorItems;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import items.genericItems.legsArmor;

public class spartanSkirt extends legsArmor {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Light Armor", "Skirt", "Legs Armor", "Bronze", "Strength", "Cloth"));
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
    
}