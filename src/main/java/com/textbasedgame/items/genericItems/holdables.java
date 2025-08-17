package com.textbasedgame.items.genericItems;
import com.textbasedgame.items.equipables;
import com.textbasedgame.monsters.monster;
import com.textbasedgame.playerFiles.player;

public abstract class holdables extends equipables{
    protected damageTypes DMGtype;
    public enum damageTypes  {INTELLIGENCE, STRENGTH, AGILITY};

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
    @Override
    public void unequipFromSlot(){
        if(player.RHand == this){
            player.RHand = null;
        }
        else if(player.LHand == this){
            player.LHand = null;
        }
        player.equippedItems.remove(this);
        
    }

    public void attackEffects(monster m){
        
    }

    public abstract int getItemDamage();
    public void setDMGType(damageTypes x){
        DMGtype =x;
    }
    public damageTypes getDMGType(){
        return DMGtype;
    }


}
