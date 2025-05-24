package items.genericItems;
import items.equipables;
import playerFiles.*;

public abstract class boots extends equipables {
    protected int armourAdd;
    protected int quality;
    
    @Override
    protected void equipToSlot() {
        player.shoes = this;
    }
    @Override
    protected void unequipFromSlot() {
        player.shoes = null;
    }

    public void onEquip(){
        player.addArmour(armourAdd);
    }
    public void onUnequip(){
        player.addArmour(armourAdd*-1);
    }
}
