package com.textbasedgame.items;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;

public abstract class equipables extends item {
    protected boolean equipped = false;
    protected String qualityString;
    protected int quality;
    public ArrayList<pair<buffTypes, Integer>> buffs;
    protected String equipmentSetName;

    
    
    public equipables(){
        setIsConsumable(false);
        quality = (int)((Math.random() * 6) +1);
        setQuality(quality);
        buffs = new ArrayList<>();
    }
    
    public pair<buffTypes,Integer> getSetBuff(){return new pair<>(null, null);}

    public void setQuality(int x){
        quality = x;
        //1.bad 2.mediocre 3.fine 4.good 5.excelent 6.perfect
        switch(x){
            case 1 -> qualityString = "Bad";
            case 2 -> qualityString = "Mediocre";
            case 3 -> qualityString = "Fine";
            case 4 -> qualityString = "Good";
            case 5 -> qualityString = "Excelent";
            case 6 -> qualityString = "Perfect";
        }
    }
    @Override
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
    @Override
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
        return getTagsSet().contains(tag);
    }
    protected abstract Set<String> getTagsSet();
    public Set<Class<? extends equipables>> getSetItems(){return new HashSet<>();}
}
