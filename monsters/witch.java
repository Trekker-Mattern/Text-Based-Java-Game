package monsters;
import world.world;
import GUI.gui;
import items.consumableItems.genericPotion;
import playerFiles.player;
import util.TrekkerMath;
public class witch extends monster{
    public witch(){
        super.setName("Witch");
        super.setStrength(TrekkerMath.randomInt(5, 1));
        super.setOrigionalHealth((int)(TrekkerMath.randomDouble(1.34, .7) * (player.playerLevel + world.stageNum + 10)));
    }
    @Override
    public String attackString(){
        return "casts a spell on you";
    }
    @Override
    public void onMonsterDeath(){
        gui.printOnGameSide("The witch drops to the ground dead and melts into the ground");
        gui.printOnGameSide("A small glass clink echoes through the chamber");
        gui.printOnGameSide("You sift through the robes and find a potion");
        player.addItemToPlayer(new genericPotion());
        gui.printOnGameSide("You scoop up the potion and put it in your pocket");
    }
}