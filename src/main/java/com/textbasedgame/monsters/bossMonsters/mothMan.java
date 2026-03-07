package com.textbasedgame.monsters.bossMonsters;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.monsters.boss;
import com.textbasedgame.monsters.monsterCreator;
import com.textbasedgame.playerFiles.*;
public class mothMan extends boss {

    public mothMan(){
        setName("Mothmann God of Moths");
        setStrength(monsterCreator.medMonsterStr(mLevel) + player.getAgility());
        setOriginalHealth(monsterCreator.medMonsterHealth(mLevel) + player.playerLevel);
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
    public Set<String> getMonsterWeakness() {
        return new HashSet<String>(Arrays.asList("Fire", "Heat"));
    }

    @Override
    public String attackString() {
        return "bites you with many moths";
    }

    @Override
    public void bossIntro(){
        gui.printOnGameSide("A man sits in the center of the room waiting patiently");
        gui.printOnGameSide("His many many eyes stare you down as he stands up");
        gui.printOnGameSide("Massive wings unfold from his back");
        gui.printOnGameSide("The wings are thin and have a pattern that looks like massive eyes");
        gui.printOnGameSide("He says nothing but gets ready to attack");
        gui.printOnGameSide("Clear that he is not a friend you do the same");
    }
}
