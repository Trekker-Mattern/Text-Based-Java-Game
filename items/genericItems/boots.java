package items.genericItems;
import items.equipables;
import playerFiles.*;

public abstract class boots extends equipables {
    protected int armourAdd;
    protected int quality;
    
    public void onEquip(){
        player.shoes = this;
        player.addArmour(armourAdd);
    }
    public void onUnequip(){
        player.shoes = null;
        player.addArmour(armourAdd*-1);
    }
}
