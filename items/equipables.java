package items;


import java.util.Set;

import GUI.gui;
import playerFiles.player;
import util.pair;

public abstract class equipables extends item {
    protected boolean equipped = false;
    protected String qualityString;
    protected int quality;
    public String buffType;
    public int buffValue;
    protected String equipmentSetName;

    
    
    public equipables(){
        setIsConsumable(false);
        quality = (int)((Math.random() * 6) +1);
        setQuality(quality);
    }
    
    public pair<String,Integer> getSetBuff(){return new pair<String,Integer>(null, null);}

    public void setQuality(int x){
        quality = x;
        //1.bad 2.mediocre 3.fine 4.good 5.excelent 6.perfect
        if(x ==1){qualityString = "Bad";}
        else if (x ==2){ qualityString = "Mediocre";}
        else if (x ==3){ qualityString = "Fine";}
        else if (x ==4){ qualityString = "Good";}
        else if (x ==5){ qualityString = "Excelent";}
        else if (x ==6){ qualityString = "Perfect";}
    
        }
    
    public void Use(){
        if(!equipped){
            player.equippedItems.add(this);
            equipToSlot();
            onEquip();
            gui.printOnGameSide("You equipped " + this.getItemName());
            equipped = true;
        }
        else{
            player.equippedItems.remove(this);
            unequipFromSlot();
            onUnequip();
            gui.printOnGameSide("You unequipped " + this.getItemName());
            equipped = false;
        }
        gui.updatePlayerSide();

    }
    public void setEquipped(boolean setEq){
        equipped = setEq;
    }
    public String getQuality(){
        return qualityString;
    }
    public int getQualityInt(){
        return quality;
    }
    public String getEquipmentSetName(){return equipmentSetName;}
    public abstract void equipToSlot();
    protected void unequipFromSlot(){}
    public void onEquip(){}
    public void onUnequip(){}
    public int getArmorVal(){return 0;}
    public boolean isEquipped(){return equipped;}
    public boolean tagsContains(String tag){
        if(getTagsSet().contains(tag)) {return true;}
        return false;
    }
    protected abstract Set<String> getTagsSet();
}
