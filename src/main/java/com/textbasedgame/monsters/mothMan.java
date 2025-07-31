package com.textbasedgame.monsters;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.*;
import com.textbasedgame.util.TrekkerMath;
public class mothMan extends boss {

    public mothMan(){
        setName("Mothmann God of Moths");
        setStrength(TrekkerMath.randomInt(10, 4));
        setOrigionalHealth((int)(TrekkerMath.randomDouble(2, 1.3) * (player.playerLevel)));
        setArmour((int)(0));
    }
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
