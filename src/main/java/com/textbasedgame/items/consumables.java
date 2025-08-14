package com.textbasedgame.items;
import com.textbasedgame.playerFiles.player;

public abstract class consumables extends item {
    protected boolean isAttackingConsumable = false;
    public consumables(){}
    @Override
    public void Use(){
        removeFromInv();
    }
    public void removeFromInv(){
        player.inventory.remove(this);
        player.consumableInv.remove(this);
    }
    @Override
    public boolean equals(Object obj) {
        
        if(obj.getClass() == this.getClass()){return true;}
        return false;
    }
}
