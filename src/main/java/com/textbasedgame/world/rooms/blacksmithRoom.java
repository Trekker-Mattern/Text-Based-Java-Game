package com.textbasedgame.world.rooms;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.GUI.pictureLoader.imageIDs;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.response;

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
        gui.printOnGameSide("Troll: Give attack stick at Iggy. Iggy make good. ");
        gui.newlOnGameSide();
        if(response.respondYes(gui.getInput("Give Iggy your weapon?"))){
            gui.printOnGameSide("Iggy jumps around happily shaking the stone floor that you stand on.");
            gui.printOnGameSide("Iggy grabs your weapon and shuffles off to the anvil. He grabs the hammer and takes a hot coal from a nearby fire.");
            gui.printOnGameSide("He smashes the coal into the weapon. The weapon glows red and Iggy throws it back at your feet.");
            gui.printOnGameSide("Iggy: Iggy make hot." + player.getName() + " make bad guys ouch.");
            gui.newlOnGameSide();

            gui.printOnGameSide("The weapon feels warm to the touch");
            gui.printOnGameSide("Iggy made the weapon so that it burns enemies. Hes not too bad of a guy after all.", gui.styles.ITALICS);

        }

    }

    @Override
    public imageIDs getRoomID(){
        return roomID;
    }
}
