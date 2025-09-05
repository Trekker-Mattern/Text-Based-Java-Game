package com.textbasedgame.world.rooms;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.response;
import com.textbasedgame.world.shopitems;
import com.textbasedgame.items.item;

public class trapRoom extends Room{
    public static final int roomID = 9;

    @Override
    public int getRoomID(){
        return roomID;
    }

    @Override
    public void openRoom(){
        gui.printOnGameSide("You enter a room with spikes lining the floor.");
        if(response.respondYes(gui.getInput("Would you like to attempt to traverse the spikes?"))){
            if(player.getAgility() >= player.getPlayerLevel()/ 3){
                gui.printOnGameSide("You dart across the spikes without a scratch, making it to the other side with ease.");
                gui.printOnGameSide("There is a chest sitting in a corner, you approach it and open the chest.");
                item i = shopitems.getRandomItem();
                gui.printOnGameSide("Inside you find a " + i.getItemName() + "!");
                player.addItemToPlayer(i);
            }
            else{
                gui.printOnGameSide("You attempt to make your way through the spikes.");
                gui.printOnGameSide("You're too clumsy and fall into a spike, taking some damage.");
                player.setHealth( player.getHealth() - (player.getPlayerLevel() - player.getAgility()));
            }
        }
        else{
            gui.printOnGameSide("You decide not to risk it and just leave the room saving your feet for another day.");
        }
    }

}
