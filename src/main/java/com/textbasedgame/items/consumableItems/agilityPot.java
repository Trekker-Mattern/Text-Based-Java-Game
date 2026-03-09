package com.textbasedgame.items.consumableItems;
import com.textbasedgame.items.consumables;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;

public class agilityPot extends consumables{
    public agilityPot(){
        setPrice(15);
        setName("Agility Potion");
        setDescription("A potion that increases your agility for a short time. The bottle vibrates softly as if the energy from the potion has imbued the bottle itself.");
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
