package com.textbasedgame.items.consumableItems;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumables;

import com.textbasedgame.playerFiles.*;


//Heals the player for maxHealth / 5
public class fish extends consumables {
    public fish(){ 
        setPrice(5);
        setName("Fish");

        try{
            if(player.getName().equals("Jesus")){
                setName("Bread (Used to be fish)");
            }
        }
        catch(Exception e){
            System.out.println("PlayerName not initialized");
        }
    }
    public void Use(){
        super.Use();
        gui.printOnGameSide("You heal for " + player.getMaxHealth()  / 5);
        player.addHealth(player.getMaxHealth()  / 5);
    }
    @Override
    public void printInfo(){
        gui.printOnGameSide("Heal Value: " + player.getMaxHealth()  / 5);
    }
}
