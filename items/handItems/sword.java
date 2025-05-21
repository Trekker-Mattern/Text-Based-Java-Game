package items.handItems;

import items.genericItems.holdables;

public class sword extends holdables {
    private int quality;
    private int swordIncrease;
    

    public sword(){
        setIsAttackingItem(true);
        swordIncrease = 2*quality;
        setPrice(30);
        setName("Sword");
        setType("Strength");
        
    }
    public int getStatIncrease(){
        return swordIncrease;
    }
    public void onEquip(){
    }
    
    public void onUnequip(){
    }
    public String getAttackString() {
        return "slice";
    }
}