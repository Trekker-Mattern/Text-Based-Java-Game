package com.textbasedgame.items.consumableItems;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumables;
import com.textbasedgame.items.item;
import com.textbasedgame.playerFiles.*;
public class bread extends consumables{
    int healthIncrease =  player.getMaxHealth()  / 3;
    public bread(){
        setPrice(7);
        setName("Bread");
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
