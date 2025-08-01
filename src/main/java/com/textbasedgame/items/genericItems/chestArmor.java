package com.textbasedgame.items.genericItems;
import com.textbasedgame.items.equipables;
import com.textbasedgame.playerFiles.player;

public abstract class chestArmor extends equipables {
    protected int armorAdd;
    
    @Override
    public void equipToSlot() {
        if(player.chestplate != null){
            player.chestplate.Use();
        }
        player.chestplate = this;
    }
    @Override
    protected void unequipFromSlot() {
        player.chestplate = null;
    }
    @Override
    public int getArmorVal(){
        return armorAdd;
    }
}
