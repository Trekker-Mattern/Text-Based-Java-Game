package com.textbasedgame.items.genericItems;
import com.textbasedgame.items.equipables;
import com.textbasedgame.playerFiles.player;

public abstract class legsArmor extends equipables {
    protected int armorAdd;
    
    @Override
    public void equipToSlot() {
        if(player.pants != null){
            player.pants.Use();
        }
        player.pants = this;
    }
    @Override
    protected void unequipFromSlot() {
        player.pants = null;
    }
    @Override
    public int getArmorVal(){
        return armorAdd;
    }
}
