package com.textbasedgame.world.rooms;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.util.*;
import com.textbasedgame.world.*;

public class portalRoom extends Room{
    private final int roomID = 5;
    public void openRoom(){
        int i = TrekkerMath.randomInt(1, 0);
            System.out.println(i);
            if(i == 0){
                gui.printOnGameSide("A portal sits in a mostly empty room, It shines with a green hue.");
                gui.printOnGameSide("Enter?");

                if(response.respondYes(gui.getInput())){
                    gui.pushOldText();
                    gui.printOnGameSide("You enter the floating spiral, slowly glancing around at the room that now surrounds you.");

                    i = TrekkerMath.randomInt(6, 0);
                    i = i - 3;
                    world.stageNum+=i;
                    if(i > 0){
                        gui.printOnGameSide("The room seems unfamiliar but you get the sense that you've made progress.");
                    }
                    else if(i < 0){
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
                    i = TrekkerMath.randomInt(2*maxAreaDecrease, 0);
                    i -= maxAreaDecrease;

                    world.AREANUM+=i;
                    world.stageNum+= i*5;
                    if(i > 0){
                        gui.printOnGameSide("The scenery has changed considerably;");
                        gui.printOnGameSide("The light around you is significantly dimmer.");
                        gui.printOnGameSide( "You believe that this portal has helped make major progress in your journey.");
                    }
                    else if(i < 0){
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
    public int getRoomID() {
        return roomID;
    }
}
