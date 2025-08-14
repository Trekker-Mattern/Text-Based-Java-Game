package com.textbasedgame.monsters.regularMonsters;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.keyItems.chestKey;
import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;
import com.textbasedgame.playerFiles.*;
public class jailer extends monster{
    public jailer(){
        super.setName("Ghastly Jailer");
        super.setStrength(monsterCreator.medMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.strongMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.slowMonsterSpeed(mLevel));
    }

    @Override
    public void onMonsterDeath(){
        gui.printOnGameSide("As the jailer drops to the floor his keys clank against the ground");
        gui.printOnGameSide("You scoop up the keys and continue on your way");
        player.addKeyItemToPlayer(new chestKey());
    }
}
