package items.genericItems;
import playerFiles.*;
import items.equipables;

public abstract class chestArmor extends equipables {
    protected int armorAdd;
    
    @Override
    protected void equipToSlot() {
        if(player.chestplate != null){
            player.chestplate.Use();
        }
        player.chestplate = this;
    }
    @Override
    protected void unequipFromSlot() {
        player.chestplate = null;
    }

    public int getArmorVal(){
        return armorAdd;
    }
}
