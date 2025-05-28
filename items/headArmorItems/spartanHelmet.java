package items.headArmorItems;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import items.genericItems.headArmor;

public class spartanHelmet extends headArmor {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Heavy Armor", "Helmet", "Head Armor", "Bronze", "Strength", "Metal"));
    public spartanHelmet(){
        armorAdd = (int)(quality * 1.8);
        setPrice(40);
        setName("Spartan Helmet");
        buffType = "Strength";
        buffValue = 3;
    }    
    protected Set<String> getTagsSet() {

        return tags;
    }
    
}
