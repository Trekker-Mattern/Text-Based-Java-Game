package items.genericItems;
import items.equipables;
import playerFiles.*;

public abstract class headArmour extends equipables {
    protected int armourAdd;
    protected int quality;

    public void onEquip(){
        player.helm = this;
        player.addArmour(armourAdd);
    }
    public void onUnequip(){
        player.helm = null;
        player.addArmour(armourAdd*-1);
    }
}
