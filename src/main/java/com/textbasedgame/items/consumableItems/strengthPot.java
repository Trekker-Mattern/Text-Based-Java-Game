package com.textbasedgame.items.consumableItems;
import com.textbasedgame.items.consumables;
import com.textbasedgame.items.item;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;

public class strengthPot extends consumables {
    public strengthPot(){
        setPrice(20);
        setName("Strength Potion");
    }

    public void Use(){
        removeFromInv();
        player.applyBuff(buffTypes.STRENGTH, 3, 4);
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = strengthPot.class;
    } 
}
