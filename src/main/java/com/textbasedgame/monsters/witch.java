package com.textbasedgame.monsters;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumableItems.genericPotion;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.world.world;
public class witch extends monster{
    public witch(){
        super.setName("Witch");
        super.setStrength(TrekkerMath.randomInt(5, 1));
        super.setOrigionalHealth((int)(TrekkerMath.randomDouble(1.34, .7) * (player.playerLevel + world.stageNum + 10)));
    }
    @Override
    public String attackString(){
        return "casts a spell on you";
    }
    @Override
    public void onMonsterDeath(){
        gui.newlOnGameSide();
        gui.printOnGameSide("The witch drops to the ground dead and melts into the ground");
        gui.printOnGameSide("A small glass clink echoes through the chamber");
        gui.printOnGameSide("You sift through the robes and find a potion");
        genericPotion pot = new genericPotion();
        player.addItemToPlayer(pot);
        gui.printOnGameSide("You scoop up the potion and put it in your pocket");
        gui.printOnGameSide("You obtain " + pot.getItemName());
    }
}