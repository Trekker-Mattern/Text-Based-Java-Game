package com.textbasedgame.world.rooms;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.util.response;

public class idolRoom extends Room {
    private final int roomID = 3;
    public void openRoom(){
        gui.printOnGameSide("You walk into a large empty cobblestoned room with a idol");
            buffTypes buffType = null;
            int i = TrekkerMath.randomInt(3, 0);
            switch(i){
                case 0:
                buffType = buffTypes.STRENGTH;
                gui.printOnGameSide("The statue seems to depict a hulking bear");
                break;
                case 1:
                buffType = buffTypes.INTELLIGENCE;
                gui.printOnGameSide("The statue seems to depict a large owl with dark Jade eyes");
                break;
                case 2:
                buffType = buffTypes.AGILITY;
                gui.printOnGameSide("The statue seems to depict a gazelle that is mid jump");
                break;
                case 3:
                buffType = buffTypes.ARMOR;
                gui.printOnGameSide("The statue seems to depict an armadillo that is curled up into a little ball");
                break;
            }
            gui.printOnGameSide("Would you like to pray to the idol?");
            String userResp = gui.getInput();
            if(response.respondYes(userResp)){
                player.applyBuff(buffType, 3, 5);
                gui.printOnGameSide("You feel blessed! The animal has improved you!");
            }
    }
    @Override
    public int getRoomID() {
        return roomID;
    }
}
