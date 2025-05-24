package items.genericItems;
import items.equipables;
import playerFiles.*;

public abstract class legsArmor extends equipables {
    protected int armourAdd;
    protected int quality;
    
    @Override
    protected void equipToSlot() {
        player.pants = this;
    }
    @Override
    protected void unequipFromSlot() {
        player.pants = null;
    }

    public void onEquip(){
        player.addArmour(armourAdd);
    }
    public void onUnequip(){
        player.addArmour(armourAdd*-1);
    }
}
