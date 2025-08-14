package com.textbasedgame.items.consumableItems;
import com.textbasedgame.GUI.gui;

import com.textbasedgame.items.genericItems.attackingConsumable;
import com.textbasedgame.playerFiles.player;

public class throwingKnife extends attackingConsumable {
    public throwingKnife(){
        setNumberInStack(3);
        setPrice(3);
        setName("Throwing Knives");
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
