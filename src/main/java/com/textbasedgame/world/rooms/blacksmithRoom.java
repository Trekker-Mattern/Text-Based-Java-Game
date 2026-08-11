package com.textbasedgame.world.rooms;

import java.util.ArrayList;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.GUI.pictureLoader.imageIDs;
import com.textbasedgame.items.genericItems.holdables;
import com.textbasedgame.items.item;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.GameProgressWrapper;
import com.textbasedgame.util.response;
import com.textbasedgame.util.selectionMenu;
import com.textbasedgame.world.world.CharacterNames;

public class blacksmithRoom extends Room{
    private static final imageIDs roomID = imageIDs.LIBRARY; 

    private void dialogue(String dialogue){
        gui.printDialogue(dialogue, CharacterNames.IGGY);
    }
    
    @Override
    public void openRoom(){
        super.openRoom();
		if(GameProgressWrapper.gameProgress.iggyMet) firstMeeting();
		else{
			dialogue("You came back to see Iggy!");
			gui.printOnGameSide("The troll jumps excitedly");
			do {
				
				IggyMenu();
			} while (response.respondYes(gui.getInput()));
		}
        
        
    }

    @Override
    public imageIDs getRoomID(){
        return roomID;
    }

	public void IggyMenu(){
		dialogue("What can Iggy do to help?");

		gui.newlOnGameSide();
		gui.printOnGameSide("Enchant a weapon");	
		gui.printOnGameSide("Rename a weapon");

		String input = gui.getInput();
		input = input.strip();
		input = input.toLowerCase();	
		if (input.contains("enchant") || input.equals("e")) {
			enchantWeaponMenu();	
		}
		else if(input.contains("rename") || input.equals("r")){
			renameWeaponMenu();
		}

		dialogue("Need more Iggy help?");
	}

	public void firstMeeting(){
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
			
			if(player.RHand == null) player.LHand.enchant("fire");
			else player.RHand.enchant("fire");
        }
		else{
			gui.printOnGameSide("You decide you couldn't possibly trust such an unpredictable creature.");
			gui.printOnGameSide("The troll looks at you sadly and sets down the pair of pliers that he was holding.");
			gui.printOnGameSide("He doesn't seem poised to attack so you follow the wall to the next door without breaking eye contact with the creature");
		}
	}


	private void enchantWeaponMenu(){
		gui.printOnGameSide("Which item would you like to enchant?");
		ArrayList<holdables> enchantList = listWeapons();

		int selection = selectionMenu.selectScreenToInteger(enchantList, gui.getInput("Which item would you like to enchant?"));
		//TODO: ADD ENCHANTS
		enchantList.get(selection).enchant("TODO ADD ENCHANTS");
		



	}

	private void renameWeaponMenu(){
		ArrayList<holdables> enchantList = listWeapons();	
		
		int selection = selectionMenu.selectScreenToInteger(enchantList, gui.getInput("Which item would you like to rename?"));
		gui.printOnGameSide("What would you like to rename" + enchantList.get(selection).toString() + "to?");
		String renameStr = gui.getInput();
		enchantList.get(selection).setName(renameStr);
		
		gui.printOnGameSide("You grab your weapon and give it a new name, declaring that it be called " + renameStr + " from now on.");
		
	}

	private ArrayList<holdables> listWeapons(){
		ArrayList<holdables> enchantList = new ArrayList<>();
		int count = 1;	

		for (item i : player.inventory) {
			if(i instanceof holdables){ enchantList.add((holdables)i);
				gui.printOnGameSide(count++ + " : " + i.toString() );
			}	
		}	


		return enchantList;
	}
}
