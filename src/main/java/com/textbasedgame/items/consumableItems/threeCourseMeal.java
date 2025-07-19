package com.textbasedgame.items.consumableItems;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumables;
import com.textbasedgame.playerFiles.*;
public class threeCourseMeal extends consumables {
    int healthIncrease =  (int)(player.getMaxHealth() * .75);
    public threeCourseMeal(){
        setPrice(25);
        setName("Three Course Meal");
    }
    public void Use(){
        removeFromInv();
        gui.printOnGameSide("You heal for " + healthIncrease);
        player.addHealth(healthIncrease);
    }
}