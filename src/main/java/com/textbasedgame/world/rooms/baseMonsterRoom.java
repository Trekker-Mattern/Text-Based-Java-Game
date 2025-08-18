package com.textbasedgame.world.rooms;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.monsters.*;
import com.textbasedgame.world.world;


public class baseMonsterRoom extends Room{
    private static final int roomID = -1;

    private monster m;

    public baseMonsterRoom(){
        m = monsterCreator.createMonster();
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
