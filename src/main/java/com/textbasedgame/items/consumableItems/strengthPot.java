package com.textbasedgame.items.consumableItems;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumables;

import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;

public class strengthPot extends consumables {
    public strengthPot(){
        setPrice(20);
        setName("Strength Potion");
    }

    public void Use(){
        super.Use();
        player.applyBuff(buffTypes.STRENGTH, 3, 4);
    }
    @Override
    public void printInfo(){
        gui.printOnGameSide("Buff Strength - 3");
        gui.printOnGameSide("Buff Length - 4");
    } 
}
