package com.textbasedgame.items.consumableItems;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumables;

import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;

public class strengthPot extends consumables {
    public strengthPot(){
        setPrice(20);
        setName("Strength Potion");
        setDescription("A glass bottle in the shape of a cube filled with bright red dull liquid. It looks about as thick as blood. You feel that drinking this liquid will make your muscles grow massively in size for a short period of time.");
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
