package items.handItems;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import items.genericItems.holdables;

public class sword extends holdables {

    private int swordIncrease;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Sword", "Strength", "Sharp", "Slicing"));

    public sword(){
        setIsAttackingItem(true);
        swordIncrease = 2*quality;
        setPrice(30);
        setName("Sword");
        setDMGType("Strength");
        itemType = "Sharp";
        
    }
    public sword(int qual){
        setQuality(qual);
        setIsAttackingItem(true);
        swordIncrease = 2*quality;
        setPrice(30);
        setName("Sword");
        setDMGType("Strength");
        itemType = "Sharp";

    }
    public int getStatIncrease(){
        return swordIncrease;
    }

    public String getAttackString() {
        return "slice";
    }
    protected Set<String> getTagsSet() {

        return tags;
    }
}