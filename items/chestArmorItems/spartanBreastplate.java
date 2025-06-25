package items.chestArmorItems;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import items.equipables;
import items.footArmorItems.spartanBoots;
import items.genericItems.chestArmor;
import items.headArmorItems.spartanHelmet;
import playerFiles.player.buffTypes;
import util.pair;

public class spartanBreastplate extends chestArmor {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Heavy Armor", "Chestplate", "Legs Armor", "Bronze", "Strength", "Metal"));
    private static final Set<Class<? extends equipables>> setItems = new HashSet<>(Arrays.asList(spartanBoots.class, spartanHelmet.class, spartanBreastplate.class));
    public spartanBreastplate(){
        armorAdd = (int)(quality * 2.2);
        setPrice(52);
        setName("Spartan Breastplate");
        equipmentSetName = "Spartan Set";
        buffType = buffTypes.STRENGTH;
        buffValue = 1;
    }
    public spartanBreastplate(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 2.2);
        setPrice(52);
        setName("Spartan Breastplate");
        buffType = buffTypes.STRENGTH;
        buffValue = 1;
    } 
    protected Set<String> getTagsSet() {

        return tags;
    }
    public static pair<buffTypes, Integer> getSetBuff() {
        return new pair<buffTypes,Integer>(buffTypes.STRENGTH, 5);
    }
    public Set<Class<? extends equipables>> getSetItems() {
        return setItems;
    }
}
