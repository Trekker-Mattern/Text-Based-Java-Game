package items.handItems;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import items.genericItems.holdables;

public class wand extends holdables {
    private int wandIncrease;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Intelligence", "Staff", "Ranged", "Magic"));

    public wand(){
        setIsAttackingItem(true);
        wandIncrease = 2*quality;
        setPrice(10);
        setName("Wand");
        setDMGType("Intelligence");
        itemType = "Magic";
    }
    public wand(int qual){
        setQuality(qual);
        setIsAttackingItem(true);
        wandIncrease = 2*quality;
        setPrice(10);
        setName("Wand");
        setDMGType("Intelligence");
        itemType = "Magic";

         
    }
    public int getStatIncrease(){
        return wandIncrease;
    }

    public String getAttackString() {
        return "cast a spell on";
    }
    protected Set<String> getTagsSet() {

        return tags;
    }
}
