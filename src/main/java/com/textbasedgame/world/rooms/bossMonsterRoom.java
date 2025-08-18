package com.textbasedgame.world.rooms;

import com.textbasedgame.monsters.*;
import com.textbasedgame.world.world;
import com.textbasedgame.GUI.*;

public class bossMonsterRoom extends Room{
    private static final int roomID = -1;

    private boss m;

    public bossMonsterRoom(){
        m = monsterCreator.createBoss();
    }

    @Override
    public void openRoom() {
        gui.pushOldText();
        world.monsterMenu(m);
    }

    public monster getMonster(){
        return m;
    };

    @Override
    public int getRoomID() {
        return roomID;
    }
}
