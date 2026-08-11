package com.textbasedgame.world.rooms;

import java.util.ArrayList;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.GUI.pictureLoader.imageIDs;
import com.textbasedgame.items.item;
import com.textbasedgame.items.consumableItems.agilityPot;
import com.textbasedgame.items.consumableItems.genericPotion;
import com.textbasedgame.items.consumableItems.intelligencePot;
import com.textbasedgame.items.consumableItems.strengthPot;
import com.textbasedgame.items.keyItems.potionsBag;
import com.textbasedgame.items.keyItems.potionsBag.potionHerbs;
import com.textbasedgame.items.genericItems.keyItem;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.world.world.CharacterNames;
import com.textbasedgame.util.GameProgressWrapper;
import com.textbasedgame.util.*;
import com.textbasedgame.util.response;
import com.textbasedgame.util.selectionMenu;

public class lizzyRoom extends Room {
    private static final imageIDs roomID = imageIDs.LIBRARY;

    /**
     * Prints dialogue from Lizzy with her character styling
     * wrapper because im lazy
     * @param dialogue
     */
    private void dialogue(String dialogue){
        gui.printDialogue(dialogue, CharacterNames.LIZZY);
    }

    @Override
    public void openRoom() {
        super.openRoom();
        gui.printOnGameSide("You enter a warmly lit room with candles and plants all over.");
        

        if(GameProgressWrapper.gameProgress.lizzyMet == false){
            gui.printOnGameSide("A lady with long curly black hair and a kind smile sits in a corner on a bed of cushions");
            gui.printOnGameSide("Strange Lady: Hello there, traveler! Its been a long time since I've had a visitor.");
            gui.printOnGameSide("Strange Lady: You can call me Lizzy! Would you like to try some of my plants?");
            GameProgressWrapper.gameProgress.lizzyMet = true;
			if(response.respondYes(gui.getInput()))talkToLizzy();
			
        }
        else{

            dialogue("Welcome back! Would you like some of my excess plants?");

			do {
				lizzyMenu();
				dialogue("Anything else you need?");
			} while (response.respondYes(gui.getInput()));

			dialogue("Come back again soon! I look forward to your return.");

        }
	}

    private void talkToLizzy(){

		if(GameProgressWrapper.gameProgress.potionBagUnlocked == false){

			dialogue("Unfortunately you dont have any way to safely carry these plants on your adventure.");
			dialogue("Come back again when you have a bag to carry them in!");
			dialogue("Since you couldn't take any plants, I will give you a small remedy that I have learned over the years.");

			player.addItemToPlayer(new genericPotion("Lizzy's Healing Potion", buffTypes.HEALTH_REGENERATION, 2, 5));
			gui.newlOnGameSide();
			gui.getInput("--Press Enter To Continue--");
		}
		else{
			potionHerbs herbToGive = potionHerbs.values()[TrekkerMath.randomInt(potionHerbs.values().length, 0)]; 
			
			int potionsBagLocationInKeyItemInventory = player.keyItemInventory.indexOf(new potionsBag());
			((potionsBag)player.keyItemInventory.get(potionsBagLocationInKeyItemInventory)).addHerbToBag(herbToGive);
			
			dialogue("I have an abundance of " + herbToGive.toString() + " today!");
			dialogue("Here, take some with you!");
			gui.newlOnGameSide();
		}

		gui.getInput("--Press Enter To Continue--");
    }
	
	private void checkoutPotions(){

		ArrayList<item> potList = new ArrayList<>();
		int count = 1;
		for (item obj : player.inventory) {
			if(obj instanceof genericPotion){
				potList.add(obj);
				count++;
				gui.printOnGameSide(count + ": " + obj.toString());
			} 	
		}

		dialogue("Which potion would you like to get more information about?");
		String response = gui.getInput();	
		Integer selectionVal = selectionMenu.selectScreenToInteger(potList, response);
		genericPotion p = (genericPotion)(potList.get(selectionVal));
		ArrayList<triple<buffTypes, Integer, Integer>> list = p.getBuffsAsList();
		
		dialogue("This potion seems to give");
		for(triple<buffTypes, Integer, Integer> b: list){
                gui.printOnGameSide(b.second + " " + b.first.toString() + " for " + b.third + " turns");
		}
		if(!p.getHerbList().isEmpty()){
			dialogue("The herbs used to make the potion are:");
			for (potionHerbs herb : p.getHerbList()) {
				gui.printOnGameSide(herb.toString());
			}
		}
	}


    public imageIDs getRoomID(){
        return roomID;
    }
	

	private void lizzyMenu(){

		gui.printOnGameSide("Explore Herbs");
		gui.printOnGameSide("Purchase Herbs");
		gui.printOnGameSide("Research Potions");

		String resp = gui.getInput();
		resp = resp.toLowerCase();
		resp = resp.strip();

		if(resp.contains("potion") || resp.contains("research") || resp.equals("r")){
			checkoutPotions();
		}
		else if(resp.contains("purchase") || resp.contains("buy") || resp.contains("shop")){
			lizzyShop();
		}
		else{
			talkToLizzy();
		}
	}

	private void lizzyShop(){
		ArrayList<pair<potionHerbs, Integer>> shopArr = generateShop();
		dialogue("Here is what I have for sale!");	
		gui.printOnGameSide("---------");
		for (int i = 0; i < shopArr.size(); i++) {
			gui.printOnGameSide((i+1) + shopArr.get(i).toString());	
		}

		int purchaseNum = selectionMenu.selectScreenToInteger(shopArr, gui.getInput());

		if(player.gold > shopArr.get(purchaseNum).second){
			gui.printOnGameSide("You buy a " + shopArr.get(purchaseNum).first.toString() + " for " + shopArr.get(purchaseNum).second + " shmeckles");
			for( keyItem item : player.keyItemInventory) {
				if(item instanceof potionsBag){
					((potionsBag)item).addHerbToBag(shopArr.get(purchaseNum).first);	
					return;
				}	
			}

		}
		else{
			dialogue("You don't have enough money to purchase that!");
		}

	}



	private ArrayList<pair<potionHerbs, Integer>> generateShop(){
		potionHerbs[] potHerbs = potionHerbs.values();
		ArrayList<pair<potionHerbs, Integer>> ret = new ArrayList<>(4);
		
		for(int i = 0; i < 4; i++){
			TrekkerMath.randomInt(potHerbs.length, 0);
			ret.add(new pair<potionHerbs, Integer>(potHerbs[i], TrekkerMath.randomInt(45, 3)));
		}

		return ret;

	}
}
