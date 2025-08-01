package com.textbasedgame.monsters;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.*;
public class mothMan extends boss {

    public mothMan(){
        setName("Mothmann God of Moths");
        setStrength(monsterCreator.medMonsterStr(mLevel) + player.getAgility());
        setOrigionalHealth(monsterCreator.medMonsterHealth(mLevel) + player.playerLevel);
        setSpeed(monsterCreator.fastMonsterSpeed(mLevel) + player.getAgility());
        setArmour((int)(0));
    }
    @Override
    public void attackEffects(int damageDoneToPlayer){
        for(int i = 0; i < player.equippedItems.size(); i++){
            if(player.equippedItems.get(i).tagsContains("Cloth")){
                gui.printOnGameSide("The giant moth sends out his swarm of moths");
                gui.printOnGameSide("They consume your" + player.equippedItems.get(i).getItemName());
                // TODO: see if this works
                player.inventory.remove(player.equippedItems.get(i));
                player.equippedItems.get(i).Use();
                player.equippedItems.remove(i);
            }
        }
    }

    @Override
    public String attackString() {
        return "bites you with many moths";
    }

}
