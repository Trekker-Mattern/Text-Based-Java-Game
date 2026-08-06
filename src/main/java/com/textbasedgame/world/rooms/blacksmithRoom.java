package com.textbasedgame.world.rooms;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.GUI.pictureLoader.imageIDs;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.response;
import com.textbasedgame.world.world.CharacterNames;

public class blacksmithRoom extends Room{
    private static final imageIDs roomID = imageIDs.LIBRARY; 

    private void dialogue(String dialogue){
        gui.printDialogue(dialogue, CharacterNames.IGGY);
    }
    
    @Override
    public void openRoom(){
        super.openRoom();

        gui.printOnGameSide("Heat blasts from this new room. You notice a grand steel anvil placed precariously on the uneven stone floor.");
        gui.printOnGameSide("Tools line the walls and a massive hammer leans against the anvil.");
        gui.printOnGameSide("Wooden doors to the right of you burst open and a massive troll jumps out.");
        gui.printOnGameSide("You ready your weapon preparing for the worst.");
        gui.newlOnGameSide();

		if(player.RHand == null && player.LHand == null){
			gui.printOnGameSide("Troll: You dont have any stick for Iggy make good...");
			gui.printOnGameSide("The troll looks at you sadly, unable to do his job.");
			gui.printOnGameSide("You turn and walk away quickly to avoid any conflict with the creature.");
			return;
		}

        gui.printOnGameSide("Troll: Give attack stick at Iggy. Iggy make good. ");
        gui.newlOnGameSide();
        if(response.respondYes(gui.getInput("Give Iggy your weapon?"))){
            
			gui.printOnGameSide("Iggy jumps around happily shaking the stone floor that you stand on.");
            gui.printOnGameSide("He grabs your weapon and shuffles off to the anvil. He grabs the hammer and takes a hot coal from a nearby fire.");
            gui.printOnGameSide("He smashes the coal into the weapon. The weapon glows red and Iggy throws it back at your feet.");
            dialogue("Iggy make hot." + player.getName() + " make bad guys ouch.");
            gui.newlOnGameSide();

            gui.printOnGameSide("The weapon feels warm to the touch");
            gui.printDialogue("Iggy made the weapon so that it burns enemies. He's not too bad of a guy after all.", CharacterNames.PLAYER);


        }
		else{
			gui.printOnGameSide("You decide you couldn't possibly trust such an unpredictable creature.");
			gui.printOnGameSide("The troll looks at you sadly and sets down the pair of pliers that he was holding.");
			gui.printOnGameSide("He doesn't seem poised to attack so you follow the wall to the next door without breaking eye contact with the creature");
		}
        
    }

    @Override
    public imageIDs getRoomID(){
        return roomID;
    }
}
