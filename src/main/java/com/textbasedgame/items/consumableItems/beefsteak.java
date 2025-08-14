package com.textbasedgame.items.consumableItems;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumables;

import com.textbasedgame.playerFiles.*;
//Heals the player for maxHealth / 2.15
public class beefsteak extends consumables{
    public beefsteak(){
        setPrice(7);
        setName("BeefSteak Sandwich");
    }
    public void Use(){
        removeFromInv();
        gui.printOnGameSide("You heal for " + (int)(player.getMaxHealth()  / 2.15));
        player.addHealth((int)(player.getMaxHealth()  / 2.15));
    }
    @Override
    public void printInfo(){
        gui.printOnGameSide("Heal Value: " + (int)(player.getMaxHealth()  / 2.15));
    }
}

