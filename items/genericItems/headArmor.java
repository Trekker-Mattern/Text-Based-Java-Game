package items.genericItems;
import items.equipables;
import playerFiles.*;
import util.pair;

public abstract class headArmor extends equipables {
    protected int armorAdd;

    public abstract pair<String, Integer> getSetBuff();

    public void equipToSlot() {
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
