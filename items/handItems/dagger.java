package items.handItems;
import items.genericItems.holdables;


public class dagger extends holdables{
    int quality;
    int damageToAdd;
    int agilityToAdd;

    public dagger(){
        setIsAttackingItem(true);
        damageToAdd = (int)(quality * 1.3);
        agilityToAdd = quality * 2;
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
}