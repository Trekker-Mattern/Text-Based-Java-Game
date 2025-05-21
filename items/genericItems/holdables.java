package items.genericItems;
import playerFiles.*;
import items.equipables;

public abstract class holdables extends equipables{
    


    @Override
    public void onEquip() {
        equipToHand();
    }

    protected void equipToHand(){
        if(player.RHand != null && player.LHand != null){
        player.askWhichHandToEquipTo(player.LHand, player.RHand);
        }
        if(player.RHand == null){
            player.RHand = this;
        }
        else if(player.LHand == null){
            player.LHand = this;
        }

    }
}
