package com.textbasedgame.items.consumableItems;
import com.textbasedgame.GUI.gui;

import com.textbasedgame.items.genericItems.attackingConsumable;
import com.textbasedgame.playerFiles.player;

public class throwingKnife extends attackingConsumable {
    public throwingKnife(){
        setNumberInStack(3);
        setPrice(3);
        setName("Throwing Knives");
        setDescription("Small intricate knives that are balanced perfectly for throwing during the heat of combat. Make sure to take them out of the bag and use them while in combat. The faster you are the faster they fall.");
    }
    @Override
    public void Use(){
        super.Use();
    }

    public int getDamageInt(){
        return player.getAgility() + player.getPlayerLevel();
    }
    public String getAttackString(){
        return "You throw the small knife with considerable force";
    }
    @Override
    public void printInfo(){
        gui.printOnGameSide("Throwing knives left: " + getStackValue());
        gui.printOnGameSide("Damage: " + getDamageInt());
    }
    @Override
    public String toString(){
        return getItemName() + " - " + getStackValue();
    }
}
