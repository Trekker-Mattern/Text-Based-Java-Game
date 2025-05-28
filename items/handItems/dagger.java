package items.handItems;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import items.genericItems.holdables;


public class dagger extends holdables{

    int damageToAdd;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Dagger", "Strength", "Agility", "Sharp", "Slicing", "Stabbing"));

    public dagger(){
        setIsAttackingItem(true);
        damageToAdd = (int)(quality * 1.3);
        buffType = "Agility";
        buffValue = quality *2;
        setPrice(15);
        setName("Dagger");
        setDMGType("Strength");
        itemType = "Sharp";
    }
    public dagger(int quality){
        setQuality(quality);
        setIsAttackingItem(true);
        damageToAdd = (int)(quality * 1.3);
        buffType = "Agility";
        buffValue = quality *2;
        setPrice(15);
        setName("Dagger");
        setDMGType("Strength");
        itemType = "Sharp";
    }

    public int getStatIncrease(){
        return damageToAdd;
    }

    public String getAttackString() {
        return "stab";
    }
    protected Set<String> getTagsSet() {

        return tags;
    }
}