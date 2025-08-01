package com.textbasedgame.items.genericItems;
import com.textbasedgame.items.equipables;
import com.textbasedgame.playerFiles.player;

public abstract class headArmor extends equipables {
    protected int armorAdd;

    @Override
    public void equipToSlot() {
        if(player.helm != null){
            player.helm.Use();
        }
        player.helm = this;
    }
    @Override
    protected void unequipFromSlot() {
        player.helm = null;
    }
    @Override
    public int getArmorVal(){
        return armorAdd;
    }
}
