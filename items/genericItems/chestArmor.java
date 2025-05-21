package items.genericItems;
import playerFiles.*;
import items.equipables;

public abstract class chestArmor extends equipables {
    protected int armourAdd;
    protected int quality;
    
    public void onEquip(){
        player.chestplate = this;
        player.addArmour(armourAdd);
    }
    public void onUnequip(){
        player.chestplate = null;
        player.addArmour(armourAdd*-1);
    }
}
