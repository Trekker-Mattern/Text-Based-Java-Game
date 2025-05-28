package items.chestArmorItems;
import items.genericItems.chestArmor;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class chestplate extends chestArmor{
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Chestplate", "Metal", "Heavy Armor", "Heavy"));
    int armourAdd;
    public chestplate(){
        armourAdd = quality * 2;
        setPrice(45);
        setName("Chestplate");

    }
    public chestplate(int quality){
        setQuality(quality);
        armourAdd = quality * 2;
        setPrice(45);
        setName("Chestplate");

    }
    public void onEquip(){
        
    }
    public void onUnequip(){

    }
    protected Set<String> getTagsSet() {

        return tags;
    }
}