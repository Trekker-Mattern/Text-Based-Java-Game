package items.handItems;
import items.genericItems.holdables;
import playerFiles.player;

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
        setType("Strength");
    }

    public int getStatIncrease(){
        return damageToAdd;
    }

    @Override
    public void onEquip(){
        equipToHand();
        player.addAgility(agilityToAdd);

    }

    @Override
    public void onUnequip(){
        player.addAgility(agilityToAdd * -1);
    }
    
    public String getAttackString() {
        return "stab";
    }
}