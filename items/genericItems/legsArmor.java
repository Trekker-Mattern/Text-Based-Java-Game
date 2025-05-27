package items.genericItems;
import items.equipables;
import playerFiles.*;

public abstract class legsArmor extends equipables {
    protected int armorAdd;
    
    @Override
    protected void equipToSlot() {
        if(player.pants != null){
            player.pants.Use();
        }
        player.pants = this;
    }
    @Override
    protected void unequipFromSlot() {
        player.pants = null;
    }

    public int getArmorVal(){
        return armorAdd;
    }
}
