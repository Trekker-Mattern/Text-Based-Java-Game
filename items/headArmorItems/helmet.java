package items.headArmorItems;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import items.genericItems.headArmor;


public class helmet extends headArmor{
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Heavy Armor", "Helmet", "Head Armor", "Metal"));
    
    public helmet(){

        armorAdd = (int)(quality * 1.3);
        setPrice(20);
        setName("Helmet");

    }
    public helmet(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.3);
        setPrice(20);
        setName("Helmet");

    }
    
    protected Set<String> getTagsSet() {

        return tags;
    }
}