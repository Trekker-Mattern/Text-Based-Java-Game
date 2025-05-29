package items.genericItems;
import items.equipables;
import playerFiles.*;

public abstract class boots extends equipables {
    protected int armorAdd;
    
    @Override
    public void equipToSlot() {
        if(player.shoes != null){
            player.shoes.Use();
        }
        player.shoes = this;
    }
    @Override
    protected void unequipFromSlot() {
        player.shoes = null;
    }

    public int getArmorVal(){
        return armorAdd;
    }
}
