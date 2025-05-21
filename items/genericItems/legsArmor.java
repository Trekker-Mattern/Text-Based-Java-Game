package items.genericItems;
import items.equipables;
import playerFiles.*;

public abstract class legsArmor extends equipables {
    protected int armourAdd;
    protected int quality;
    
    public void onEquip(){
        player.pants = this;
        player.addArmour(armourAdd);
    }
    public void onUnequip(){
        player.pants = null;
        player.addArmour(armourAdd*-1);
    }
}
