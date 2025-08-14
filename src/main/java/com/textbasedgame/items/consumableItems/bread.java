package com.textbasedgame.items.consumableItems;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumables;

import com.textbasedgame.playerFiles.*;
//Heals the player for maxHealth / 3
public class bread extends consumables{
    public bread(){
        setPrice(7);
        setName("Bread");
    }
    public void Use(){
        super.Use();
        gui.printOnGameSide("You heal for " + (player.getMaxHealth()  / 3));
        player.addHealth(player.getMaxHealth()  / 3);
    }
    @Override
    public void printInfo(){
        gui.printOnGameSide("Heal Value: " + (player.getMaxHealth()  / 3));
    }
}
