package items.handItems;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import items.genericItems.holdables;

public class club extends holdables {
    private int increase;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Strength", "Blunt", "Club", "Wood"));

    public club(){
        setIsAttackingItem(true);
        increase = (int)(1.7*quality);
        setPrice(30);
        setName("Club");
        setDMGType("Strength");
        itemType = "Blunt";
        
    }
    public club(int quality){
        setQuality(quality);
        setIsAttackingItem(true);
        increase = (int)(1.7*quality);
        setPrice(30);
        setName("Club");
        setDMGType("Strength");
        itemType = "Blunt";
    }
    public int getStatIncrease(){
        return increase;
    }

    public String getAttackString() {
        return "bonk";
    }
    protected Set<String> getTagsSet() {

        return tags;
    }
}