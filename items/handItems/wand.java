package items.handItems;

import items.genericItems.holdables;

public class wand extends holdables {
    private int quality;
    private int wandIncrease;
    

    public wand(){
        setIsAttackingItem(true);
        wandIncrease = 2*quality;
        setPrice(10);
        setName("Wand");
        setType("Intelligence");
        
    }
    public int getStatIncrease(){
        return wandIncrease;
    }
    public void onEquip(){
    }
    
    public void onUnequip(){
    }
    public String getAttackString() {
        return "cast a spell on";
    }
}
