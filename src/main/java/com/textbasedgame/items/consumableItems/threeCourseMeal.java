package com.textbasedgame.items.consumableItems;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumables;
import com.textbasedgame.items.item;
import com.textbasedgame.playerFiles.*;
// Heals the player for 0.75 * maxHealth
public class threeCourseMeal extends consumables {
    public threeCourseMeal(){
        setPrice(25);
        setName("Three Course Meal");
    }
    public void Use(){
        removeFromInv();
        gui.printOnGameSide("You heal for " + (int)(player.getMaxHealth() * .75));
        player.addHealth((int)(player.getMaxHealth() * .75));
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = threeCourseMeal.class.getName();
    } 
    @Override
    public void printInfo(){
        gui.printOnGameSide("Heal Value: " + (int)(player.getMaxHealth() * .75));
    }
}