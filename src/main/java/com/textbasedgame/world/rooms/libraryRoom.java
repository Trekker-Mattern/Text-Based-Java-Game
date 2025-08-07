package com.textbasedgame.world.rooms;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.util.response;
import com.textbasedgame.world.roomFactory;
public class libraryRoom extends roomFactory {
    private final int roomID = 4;
    public void openRoom(){
        
        gui.printOnGameSide("You step into a brand new room and are surrounded by books");
        gui.printOnGameSide("Pick a book off a shelf and read it?");
        if(response.respondYes(gui.getInput())){
            gui.pushOldText();
            int i = TrekkerMath.randomInt(3, 0);
            switch(i){
                case 0:{
                    gui.printOnGameSide("You grab a book titled \"Bodybuilding for Dummies\" ");
                    player.intelligence--;
                    player.strength += 2;
                    gui.printOnGameSide("You feel your muscles grow and your mind shrink");
                    break;
                }
                case 1:{
                    gui.printOnGameSide("You grab a book titled \" Astrophysics: The Magic Way - By: Dr. WikedSmaht\"");
                    player.intelligence += 2;
                    gui.printOnGameSide("You feel significantly smarter after studying the workings of the universe");
                    break;
                }
                case 2: {
                    gui.printOnGameSide("You grab a book titled \"Go Fast Like Horse: 3 Easy Steps!\"");
                    player.agility +=4;
                    gui.printOnGameSide("The book helps you rediscover the way you walk and provides a new outlook on moving!");
                    break;
                }
                case 3:{
                    gui.printOnGameSide("You grab a book titled \" Common Enemies of the World - By: Ad-Venturer \"");
                    player.gainXP((player.getXpToLevelUp() - player.getXP())+1);
                    gui.printOnGameSide("You feel like you have a better grasp on the world around you!");
                    break;
                }
            }
        }
        
    }
    @Override
    public int getRoomID() {
        return roomID;
    }
}
