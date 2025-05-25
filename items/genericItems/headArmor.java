package items.genericItems;
import items.equipables;
import playerFiles.*;

public abstract class headArmor extends equipables {
    protected int armorAdd;
    protected int quality;

    
    protected void equipToSlot() {
        if(player.helm != null){
            player.helm.Use();
        }
        player.helm = this;
    }
    protected void unequipFromSlot() {
        player.helm = null;
    }

    public int getArmorVal(){
        return armorAdd;
    }
}
