package com.textbasedgame.items.consumableItems;
import com.textbasedgame.items.consumables;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;
public class intelligencePot extends consumables {
    public intelligencePot(){
        setPrice(20);
        setName("Intelligence Potion");
    }

    public void Use(){
        removeFromInv();
        player.applyBuff(buffTypes.INTELLIGENCE, 3, 4);
    }
}
