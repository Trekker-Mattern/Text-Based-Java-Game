package items.genericItems;
import playerFiles.*;
import items.equipables;

public abstract class chestArmor extends equipables {
    protected int armourAdd;
    protected int quality;
    
    @Override
    protected void equipToSlot() {
        player.chestplate = this;
    }
    @Override
    protected void unequipFromSlot() {
        player.chestplate = null;
    }

    public void onEquip(){
        player.addArmour(armourAdd);
    }
    public void onUnequip(){
        player.addArmour(armourAdd*-1);
    }
}
