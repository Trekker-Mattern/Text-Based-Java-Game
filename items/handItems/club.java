package items.handItems;

import items.genericItems.holdables;

public class club extends holdables {
    private int quality;
    private int increase;
    

    public club(){
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
}