package items.handItems;

import items.genericItems.holdables;

public class wand extends holdables {
    private int wandIncrease;
    

    public wand(){
        setIsAttackingItem(true);
        wandIncrease = 2*quality;
        setPrice(10);
        setName("Wand");
        setDMGType("Intelligence");
        itemType = "Magic";
    }
    public wand(int quality){
        setQuality(quality);
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
}
