package com.textbasedgame.world.rooms;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumableItems.genericPotion;
import com.textbasedgame.items.keyItems.potionsBag;
import com.textbasedgame.items.keyItems.potionsBag.potionHerbs;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.GameProgressWrapper;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.util.response;

public class lizzyRoom extends Room {
    private static final int roomID = 10;
    public void openRoom() {
        gui.printOnGameSide("You enter a warmly lit room with candles and plants all over.");
        

        if(GameProgressWrapper.gameProgress.lizzyMet == false){
            gui.printOnGameSide("A lady with long curly black hair and a kind smile sits in a corner on a bed of cushions");
            gui.printOnGameSide("Strange Lady: Hello there, traveler! Its been a long time since I've had a visitor.");
            gui.printOnGameSide("Strange Lady: You can call me Lizzy! Would you like to try some of my plants?");
            GameProgressWrapper.gameProgress.lizzyMet = true;
        }
        else{
            gui.printOnGameSide("Lizzy: Welcome back! Would you like some of my excess plants?");
        }
        talkToLizzy();
    }

    private void talkToLizzy(){
        if(response.respondYes(gui.getInput())){
            if(GameProgressWrapper.gameProgress.potionBagUnlocked == false){
                gui.printOnGameSide("Lizzy: Unfortunately you dont have any way to safely carry these plants on your adventure.");
                gui.printOnGameSide("Lizzy: Come back again when you have a bag to carry them in!");
                gui.printOnGameSide("Lizzy: Since you couldnt take any plants, I will give you a small remedy that I have learned over the years.");
                player.addItemToPlayer(new genericPotion("Lizzy's Healing Potion", buffTypes.HEALTH_REGENERATION, 2, 5));
            }
            else{
                potionHerbs herbToGive = potionHerbs.values()[TrekkerMath.randomInt(0, potionHerbs.values().length - 1)]; 
                gui.printOnGameSide("Lizzy: I have an abundance of " + herbToGive.toString() + " today!");
                gui.printOnGameSide("Lizzy: Here, take some with you!");
                int potionsBagLocationInKeyItemInventory =player.keyItemInventory.indexOf(new potionsBag());
                ((potionsBag)player.keyItemInventory.get(potionsBagLocationInKeyItemInventory)).addHerbToBag(herbToGive);
            }
        }
        else{
            gui.printOnGameSide("Lizzy: Thats okay! Return soon!");
        }
    }

    public int getRoomID(){
        return roomID;
    }

}
