package items.genericItems;
import items.equipables;
import playerFiles.*;

public abstract class headArmour extends equipables {
    protected int armourAdd;
    protected int quality;

    
    protected void equipToSlot() {
        player.helm = this;
    }
    protected void unequipFromSlot() {
        player.helm = null;
    }

    public void onEquip(){
        player.addArmour(armourAdd);
    }
    public void onUnequip(){
        player.addArmour(armourAdd*-1);
    }
}
