package monsters;
import playerFiles.*;
import GUI.gui;
import world.world;
import util.TrekkerMath;
import util.response;
import items.*;
import items.handItems.hydraHead;

public class hydra extends boss {
    public hydra(){
        setName("Hydra");
        setStrength(TrekkerMath.randomInt(14, 7) + world.AREANUM);
        setOrigionalHealth((int)(TrekkerMath.randomDouble(3, 2.5) * (player.playerLevel + world.AREANUM + 4)));
    }
    public void attackEffects(int damageDoneToPlayer){

        ///////////////////////
        /// FIX HYDRA BASED ON HAND --- Fixed
        /// /////////////////
            if((player.RHand != null && player.RHand.tagsContains("Sharp")) ||  (player.LHand != null || player.LHand.tagsContains("Sharp"))){
                gui.printOnGameSide("You chop off a head but it grows 2 more");
                setHealth(getHealth()*2);
                setStrength(getStrength()*2); 
            }

    }
    @Override
    public String attackString() {
        return "bites you with one of its many heads";
    }

    @Override
    public void onMonsterDeath() {
        gui.printOnGameSide("The slain beast lies at your feet, the many heads still wiggling.");
        gui.printOnGameSide("You think they could be of use to you.");
        gui.printOnGameSide("Do you chop one off and take it with you?");
        if(response.respondYes(gui.getInput())){
            for(equipables e : player.equippedItems){
                if(e.tagsContains("Sharp") || e.tagsContains("Slicing")){
                    gui.printOnGameSide("You successfully remove a head from the beast and attach it to your belt");
                    player.addItemToPlayer(new hydraHead());
                    return;
                }
            }
            gui.printOnGameSide("You dont have anything to properly slice the head off so you leave the corpse.");
        }
    }
}
