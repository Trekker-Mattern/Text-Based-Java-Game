package com.textbasedgame.items.consumableItems;
import com.textbasedgame.items.consumables;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;
public class intelligencePot extends consumables {
    public intelligencePot(){
        setPrice(20);
        setName("Intelligence Potion");
        setDescription("A vial that shines brightly and sparkles similarly to the stars in the sky. Drinking this will surely make you smarter, as do most glowing mysterious liquids.");
    }

    public void Use(){
        super.Use();
        player.applyBuff(buffTypes.INTELLIGENCE, 3, 4);
    }
    @Override
    public void printInfo(){
        gui.printOnGameSide("Buff Strength - 3");
        gui.printOnGameSide("Buff Length - 4");
    } 
}
