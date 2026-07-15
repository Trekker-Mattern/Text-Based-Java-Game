package com.textbasedgame.world.rooms;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.GUI.pictureLoader.imageIDs;
import com.textbasedgame.util.*;
import com.textbasedgame.world.*;

public class portalRoom extends Room{
    private static final imageIDs roomID = imageIDs.PORTALROOM;
    private int portalType;
    private int stageShift;

    public void openRoom(){
        portalType = TrekkerMath.randomInt(2, 0);
            System.out.println(portalType);
            if(portalType == 0){
                gui.printOnGameSide("A portal sits in a mostly empty room, It shines with a green hue.");
                gui.printOnGameSide("Enter?");

                if(response.respondYes(gui.getInput())){
                    gui.pushOldText();
                    gui.printOnGameSide("You enter the floating spiral, slowly glancing around at the room that now surrounds you.");

                    stageShift = TrekkerMath.randomInt(7, 0);
                    stageShift = stageShift - 3;
                    world.stageNum+=stageShift;
                    if(stageShift > 0){
                        gui.printOnGameSide("The room seems unfamiliar but you get the sense that you've made progress.");
                    }
                    else if(stageShift < 0){
                        gui.printOnGameSide("This room feels brutally familiar, reminding you of recent battles fought.");
                        gui.printOnGameSide( "You sigh knowing you have a longer way to go to progress now.");
                    }
                    else{
                        gui.printOnGameSide("The room looks exactly like the room you just left. You raise an eyebrow and continue on.");
                    }
                }

            }
            else{
                gui.printOnGameSide("A portal sits in a mostly empty room, It shines with a blue hue.");
                gui.printOnGameSide("Enter?");

                if(response.respondYes(gui.getInput())){
                    gui.pushOldText();
                    gui.printOnGameSide("You enter the floating spiral, slowly glancing around at the room that now surrounds you.");
                    int maxAreaDecrease;
                    if(world.AREANUM < 3) {maxAreaDecrease = world.AREANUM;}
                    else{maxAreaDecrease = 3;}
                    stageShift = TrekkerMath.randomInt(2*maxAreaDecrease + 1, 0);
                    stageShift -= maxAreaDecrease;

                    world.AREANUM+=stageShift;
                    world.stageNum+= stageShift*5;
                    if(stageShift > 0){
                        gui.printOnGameSide("The scenery has changed considerably;");
                        gui.printOnGameSide("The light around you is significantly dimmer.");
                        gui.printOnGameSide( "You believe that this portal has helped make major progress in your journey.");
                    }
                    else if(stageShift < 0){
                        gui.printOnGameSide("The scenery has changed considerably;");
                        gui.printOnGameSide("The flooring and walls look too familiar, closely resembling areas from the past.");
                        gui.printOnGameSide("The sickening sensation of lost progress sets in");
                    }
                    else{
                        gui.printOnGameSide("Nothing major changed. Actually nothing changed at all. You shrug and continue on.");
                    }
                }
            }
    }
    @Override
    public imageIDs getRoomID() {
        return roomID;
    }
}
