package com.textbasedgame.world.rooms;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.GUI.pictureLoader.imageIDs;

public class blacksmithRoom extends Room{
    private static final imageIDs roomID = imageIDs.LIBRARY; 
    
    @Override
    public void openRoom(){
        super.openRoom();

        gui.printOnGameSide("Heat blasts from this new room. You notice a grand steel anvil placed precariously on the uneven stone floor.");
        gui.printOnGameSide("Tools line the walls and a massive hammer leans against the anvil.");
        gui.printOnGameSide("Wooden doors to the right of you burst open and a massive troll jumps out.");
        gui.printOnGameSide("You ready your weapon preparing for the worst.");
        gui.newlOnGameSide();
        gui.printOnGameSide("Troll: Give attack stick at iggy. Iggy make good. ");
        gui.newlOnGameSide();
        if(false){}

    }

    @Override
    public imageIDs getRoomID(){
        return roomID;
    }
}
