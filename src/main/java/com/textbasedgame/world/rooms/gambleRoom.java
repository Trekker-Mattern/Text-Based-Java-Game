package com.textbasedgame.world.rooms;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.GUI.pictureLoader.imageIDs;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.util.response;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;


public class gambleRoom extends Room{
    public static final imageIDs roomID = imageIDs.LIBRARY;
    private int gambleAmount;
    private enum gambleSlots { CLUB, SPADE, DIAMOND, COIN}
    
    @Override
    public void openRoom(){
        super.openRoom();
        gui.printOnGameSide("A stone machine lays dorment in the center of a dimly lit room");
        gui.printOnGameSide("It looks like it takes a coin as a startup mechanism.");
        if(response.respondYes(gui.getInput("Insert Coin?"))){
            
            gambleAmount = Integer.parseInt(gui.getInput("How many shmeckles would you like to gamble?"));

            if(player.gold >= gambleAmount){
                player.gold -= gambleAmount;

                gui.printOnGameSide("You grab" + gambleAmount + "shmeckles from your coin pouch and toss them into the machine.");
                gui.printOnGameSide("With each coin insertion the machine clicks ominously.");
                gui.printOnGameSide("You pull the lever and the machine starts spinning and shaking violently");
                gui.newlOnGameSide();
                gui.printOnGameSide("The machine stops spinning one segment at a time from left to right.");
                gui.newlOnGameSide();
                
                int slotOne = TrekkerMath.randomInt(0, 4);
                int slotTwo = TrekkerMath.randomInt(0, 4);
                int slotThree = TrekkerMath.randomInt(0, 4);

                gui.printOnGameSide("The first slot lands on " + gambleSlots.values()[slotOne]);
                gui.getInput("-- Press Enter to see the next result -- ");
                gui.newlOnGameSide();

                gui.printOnGameSide("The second slot lands on " + gambleSlots.values()[slotTwo]);
                gui.getInput("-- Press Enter to see the next result -- ");
                gui.newlOnGameSide();

                gui.printOnGameSide("The third slot lands on " + gambleSlots.values()[slotThree]);
                gui.newlOnGameSide();
                
                // Player Wins Jackpot
                if(slotOne == slotTwo && slotTwo == slotThree){
                    gui.printOnGameSide("The walls shake with the immense amount of chimes and music erupting from the machine");
                    gui.printOnGameSide("You deduce that you win and then a massive amount of coins start flying in every direction");
                    
                    
                    int gambleWinnings = gambleAmount * 10; 
                    player.gold += gambleAmount * 10; 
                    
                    gui.printOnGameSide("After picking the money up you recount your shmeckles and find you made" + gambleWinnings);
                    gui.printOnGameSide("Your final shmeckle count is " + player.gold);
                }
                else if (slotOne == slotTwo || slotTwo == slotThree || slotOne == slotThree){
                    gui.printOnGameSide("A quiet jingle starts playing from the machine and coins spill out from the slot.");
                    
                    int gambleWinnings = (int)(gambleAmount * 1.5); 
                    player.gold += (int)(gambleAmount * 1.5); 
                    
                    gui.printOnGameSide("After picking the money up you recount your shmeckles and find you made" + gambleWinnings);
                    gui.printOnGameSide("Your final shmeckle count is " + player.gold);
                }
                else{
                    gui.printOnGameSide("The machine stops quietly and you decide this old piece of trash isnt worth your time.");
                }

            }
            else{
                gui.printOnGameSide("You take every last coin out of your pockets and are still found wanting.");
                if(player.isMajorityStat(buffTypes.INTELLIGENCE)){gui.printOnGameSide("No amount of Wizardry can summon the amount of coins you need to gamble.");}
            }
        }
        else{
            gui.printOnGameSide("You walk away from the machine and continue on your journey");
        }


    }

    @Override 
    public imageIDs getRoomID() {
        return roomID;
    }
}
