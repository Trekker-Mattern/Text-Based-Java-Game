package com.textbasedgame.items.genericItems;
import com.textbasedgame.items.equipables;
import com.textbasedgame.playerFiles.*;

public abstract class headArmor extends equipables {
    protected int armorAdd;


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
