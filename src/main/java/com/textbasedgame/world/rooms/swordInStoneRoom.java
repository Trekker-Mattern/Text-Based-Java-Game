package com.textbasedgame.world.rooms;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.handItems.escalibur;
import com.textbasedgame.monsters.regularMonsters.demon;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.response;
import com.textbasedgame.world.roomFactory;
import com.textbasedgame.world.world;

public class swordInStoneRoom extends roomFactory {
    private final int roomID = 6;
    @Override
    public void openRoom() {
        gui.printOnGameSide("A massive stone sits in the center of the room");
        gui.printOnGameSide("You walk up to it and see a sword with its hilt to the ceiling");
        gui.printOnGameSide("The handle glows with a white light");

        gui.printOnGameSide("Do you attempt to remove the sword from the stone?");
        String resp = gui.getInput();
        if(response.respondYes(resp)){
            gui.printOnGameSide("You pull the sword from the stone and a cheer of angels surrounds you");
            player.addItemToPlayer(new escalibur());

            gui.printOnGameSide("The balance between light and dark has been broken");
            gui.printOnGameSide("The floor opens in front of you and a demon emerges from the crevice");
            world.monsterMenu(new demon());
        }
    }
    @Override
    public int getRoomID() {
        return roomID;
    }
}
