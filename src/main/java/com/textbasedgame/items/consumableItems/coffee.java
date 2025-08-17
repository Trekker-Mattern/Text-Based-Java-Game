package com.textbasedgame.items.consumableItems;

import com.textbasedgame.playerFiles.*;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.GUI.*;
import com.textbasedgame.items.consumables;

public class coffee extends consumables{
    public coffee(){
        setPrice(10);
        setName("Coffee");
    }
    public void Use(){
        super.Use();
        gui.printOnGameSide("You heal for " + (player.getMaxHealth()  / 3));
        gui.printOnGameSide("You feel extremely energized!");
        player.addHealth(player.getMaxHealth()  / 3);
        player.applyBuff(buffTypes.AGILITY, 2, 4);
    }
    @Override
    public void printInfo(){
        gui.printOnGameSide("Heal Value: " + (player.getMaxHealth()  / 3));
        gui.printOnGameSide("2 Agility Buff for 4 turns");
    }
}
