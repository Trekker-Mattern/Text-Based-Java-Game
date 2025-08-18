package com.textbasedgame.world.rooms;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.response;

public class soulWeighingRoom extends Room{
    private static final int roomID = 7;
    @Override
    public void openRoom(){
        gui.printOnGameSide("You walk into a room containing large golden scales");
        gui.printOnGameSide("A mirror seperates the two sides");
        gui.printOnGameSide("Would you like to step onto the scales to weigh your soul?");

        if(response.respondYes(gui.getInput())){
            gui.printOnGameSide("You approach the scale and step on.");
            if(player.strength == player.agility && player.agility == player.intelligence){
                gui.printOnGameSide("Your soul has been proven pure and you feel weight lifted off your shoulders");
                player.strength++;
                player.agility++;
                player.intelligence++;
                player.health += 3;
            }
            else if(player.getStrength() == player.getAgility() && player.getAgility() == player.getIntelligence()){
                gui.printOnGameSide("Your soul may not have been truly pure but the scale was unable to detect it");
                gui.printOnGameSide("You feel slightly lighter as you step off the scale, but it is not without guilt");
                player.health += 10;
            }
            else if(Math.abs(player.strength - player.agility) < 2 && Math.abs(player.agility - player.intelligence) < 2 && Math.abs(player.strength - player.intelligence) < 2){
                gui.printOnGameSide("Your soul was dirty but only slightly");
                gui.printOnGameSide("The golden scales decide to throw you a bone");
                player.health += 3;
                player.applyBuff(buffTypes.AGILITY, 1, 5);
                player.applyBuff(buffTypes.STRENGTH, 1, 5);
                player.applyBuff(buffTypes.INTELLIGENCE, 1, 5);
            }
            else{
                gui.printOnGameSide("The scales pause for a moment");
                gui.printOnGameSide("Then launch you off towards the door with incredible force");
                if(player.health > 4){
                    player.health -= 4;
                }
                else{
                    player.health--;
                    if(player.health == 0){
                        player.death();

                        gui.printOnGameSide("Would you like to respawn?");
                        if(response.respondNo(gui.getInput())){
                            gui.quit();
                        }
                        else{
                            player.onRespawn();
                        }
                    }
                }
            }
        }
        else{
            gui.printOnGameSide("You decide that your soul best not be weighted and continue on your way");
        }
    }
    @Override
    public int getRoomID(){
        return roomID;
    }
}
