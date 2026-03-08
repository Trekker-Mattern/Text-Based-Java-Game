package com.textbasedgame.world.rooms;

import com.textbasedgame.monsters.*;
import com.textbasedgame.GUI.*;
import com.textbasedgame.GUI.pictureLoader.imageIDs;

public class bossMonsterRoom extends baseMonsterRoom{
    private static final imageIDs roomID = imageIDs.CAVE;

    private boss m;

    public bossMonsterRoom(){
        m = monsterCreator.createBoss();
    }

    @Override
    public void openRoom() {
        m.bossIntro();
        gui.getInput("--Enter To Continue--");
        gui.pushOldText();
        monsterMenu(m);
    }

    public monster getMonster(){
        return m;
    };

    @Override
    public imageIDs getRoomID() {
        return roomID;
    }
}
