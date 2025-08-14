package com.textbasedgame.items.consumableItems;
import com.textbasedgame.GUI.gui;

import com.textbasedgame.items.genericItems.attackingConsumable;
import com.textbasedgame.playerFiles.player;

public class throwingKnife extends attackingConsumable {
    int amountInSet = 3;
    public throwingKnife(){
        setPrice(3);
        setName("Throwing Knives");
    }
    @Override
    public void Use(){
        amountInSet--;
        if(amountInSet <= 0){
            removeFromInv();
        }

    }

    public int getDamageInt(){
        return player.getAgility() + player.getPlayerLevel();
    }
    public String getAttackString(){
        return "You throw the small knife with considerable force";
    }
    @Override
    public void printInfo(){
        gui.printOnGameSide("Throwing knives left: " + amountInSet);
        gui.printOnGameSide("Damage: " + getDamageInt());
    }
    @Override
    public String toString(){
        return getItemName() + " - " + amountInSet;
    }
}
