package com.textbasedgame.items.consumableItems;
import com.textbasedgame.items.consumables;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;

public class agilityPot extends consumables{
    public agilityPot(){
        setPrice(15);
        setName("Agility Potion");
    }

    public void Use(){
        removeFromInv();
        player.applyBuff(buffTypes.AGILITY, 7, 4);
    }
}
