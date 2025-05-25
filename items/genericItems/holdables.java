package items.genericItems;
import playerFiles.*;
import items.equipables;

public abstract class holdables extends equipables{
    protected String DMGtype;
    protected String itemType;

    @Override
    public void onEquip() {}

    @Override
    protected void equipToSlot() {
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

    public void setDMGType(String x){
        DMGtype =x;
    }
    public String getDMGType(){
        return DMGtype;
    }

    protected void setItemType(String s){
        itemType = s;
    }
    public String getItemType(){
        return itemType;
    }
    
}
