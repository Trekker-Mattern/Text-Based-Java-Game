package com.textbasedgame.world.rooms;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.handItems.sword;
import com.textbasedgame.monsters.goblin;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.*;
import com.textbasedgame.world.*;

public class fountainRoom extends roomFactory {
    private final int roomID = 2;
    public void openRoom(){
        gui.printOnGameSide("You walk into a room with a fountain in the center.");
        gui.printOnGameSide("The room is too dark to see what color the water is");

        gui.printOnGameSide("Take a drink?");
        String userResp = gui.getInput();
        gui.pushOldText();
        if(response.respondYes(userResp)){
            int i = TrekkerMath.randomInt(2, 0);
            switch(i){
                case 0:
                    gui.printOnGameSide("The water was crystal clear! You feel your wounds healing! Must've been a magic fountain!");
                    player.addHealth(player.getMaxHealth() - player.getHealth());
                    break;
                case 1:
                    gui.printOnGameSide("The water tasted slightly metallic. You ignore the strange taste and continue drinking.");
                    player.addHealth(player.getMaxHealth() / 10);
                    gui.printOnGameSide("Do you decide to reach into the water to see what lies beneath the surface?");
                    userResp = gui.getInput();
                    if(response.respondYes(userResp)){
                        i = TrekkerMath.randomInt(2, 0);
                        switch(i){
                            case 0:
                            gui.printOnGameSide("You reach in and find coins!");
                            int coinage = TrekkerMath.randomInt(world.stageNum + 20, 0);
                            gui.printOnGameSide("You gain " + coinage + " shmeckles!");
                            break;
                            case 1:
                            gui.printOnGameSide("You reach in and find a sword lying under the surface of the water!");
                            player.addItemToPlayer(new sword());
                            break;
                            case 2:
                            gui.printOnGameSide("A goblin grabs your arm! In your haste to recover he takes off three of your fingers!");
                            player.health -= 10;
                            goblin g = new goblin();
                            world.monsterMenu(g);
                            break;
                        }
                    }
                    break;
                case 2:
                    gui.printOnGameSide("The water tastes like garbage! You turn around and immediately vomit!");
                    int h = player.health /= 4;
                    gui.printOnGameSide("You manage to keep your intestines inside of you. You have " + h + " health left.");
                    break;
            }
        }
    }
    @Override
    public int getRoomID() {
        return roomID;
    }
}
