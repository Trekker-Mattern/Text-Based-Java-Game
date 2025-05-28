package items.footArmorItems;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import items.genericItems.boots;

public class clogs extends boots {
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Clogs", "Wood", "Shoes", "Boots", "Heavy", "Heavy Armor"));
    public clogs(){

        armorAdd = (int)(quality * .75);
        setPrice(5);
        setName("Clogs");

    }
    protected Set<String> getTagsSet() {

        return tags;
    }
}
