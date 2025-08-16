package com.textbasedgame.items.keyItems;

import com.textbasedgame.items.genericItems.keyItem;
import com.textbasedgame.playerFiles.player;

public class chestKey extends keyItem{
    @Override
    public void Use(){
        player.keyItemInventory.remove(this);
    }
    @Override
    public void printInfo(){}
    @Override
    public String toString(){
        return "Chest Key";
    }
}
