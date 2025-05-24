package items.genericItems;
import playerFiles.*;
import items.equipables;

public abstract class holdables extends equipables{
    
    protected String itemType;

    @Override
    public void onEquip() {}

    @Override
    protected void equipToSlot() {
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


    protected void setItemType(String s){
        itemType = s;
    }
    public String getItemType(){
        return itemType;
    }
    
}
