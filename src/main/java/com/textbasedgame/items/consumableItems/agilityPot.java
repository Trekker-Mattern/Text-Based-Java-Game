package com.textbasedgame.items.consumableItems;
import com.textbasedgame.items.consumables;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;

public class agilityPot extends consumables{
    public agilityPot(){
        setPrice(15);
        setName("Agility Potion");
    }

    public void Use(){
        super.Use();
        player.applyBuff(buffTypes.AGILITY, 4, 7);
    }
    @Override
    public void printInfo(){
        gui.printOnGameSide("Buff Strength - 3");
        gui.printOnGameSide("Buff Length - 4");
    } 
}
