package com.textbasedgame.items.genericItems;
import com.textbasedgame.items.equipables;
import com.textbasedgame.playerFiles.*;

public abstract class holdables extends equipables{
    protected String DMGtype;


    @Override
    public void onEquip() {}

    @Override
    public void equipToSlot() {
        if(player.RHand != null && player.LHand != null){
        player.askWhichHandToEquipTo(this);

        }
        if(player.RHand == null){
            player.RHand = this;
        }
        else if(player.LHand == null){
            player.LHand = this;
        }
    }

    public void unequipFromSlot(){
        if(player.RHand == this){
            player.RHand = null;
        }
        else if(player.LHand == this){
            player.LHand = null;
        }
        player.equippedItems.remove(this);
        
    }

    public void setDMGType(String x){
        DMGtype =x;
    }
    public String getDMGType(){
        return DMGtype;
    }


}
