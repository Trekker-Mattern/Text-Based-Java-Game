package com.textbasedgame.items.consumableItems;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumables;
import com.textbasedgame.items.item;
import com.textbasedgame.playerFiles.*;
public class beefsteak extends consumables{
    int healthIncrease =  (int)(player.getMaxHealth()  / 2.15);
    public beefsteak(){
        setPrice(7);
        setName("BeefSteak Sandwitch");
    }
    public void Use(){
        removeFromInv();
        gui.printOnGameSide("You heal for " + healthIncrease);
        player.addHealth(healthIncrease);
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = bread.class.getName();
    } 
    @Override
    public void printInfo(){
        gui.printOnGameSide("Heal Value: " + healthIncrease);
    }
}

