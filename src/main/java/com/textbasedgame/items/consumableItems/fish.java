package com.textbasedgame.items.consumableItems;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumables;
import com.textbasedgame.items.item;
import com.textbasedgame.playerFiles.*;
public class fish extends consumables {
    int healthIncrease =  player.getMaxHealth()  / 5;
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
        removeFromInv();
        gui.printOnGameSide("You heal for " + healthIncrease);
        player.addHealth(healthIncrease);
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = fish.class.getName();
    } 
    @Override
    public void printInfo(){
        gui.printOnGameSide("Heal Value: " + healthIncrease);
    }
}
