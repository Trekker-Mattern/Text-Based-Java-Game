package items.handItems;

import items.genericItems.holdables;

public class sword extends holdables {

    private int swordIncrease;
    

    public sword(){
        setIsAttackingItem(true);
        swordIncrease = 2*quality;
        setPrice(30);
        setName("Sword");
        setDMGType("Strength");
        itemType = "Sharp";
        
    }
    public sword(int quality){
        setQuality(quality);
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
}