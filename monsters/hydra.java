package monsters;
import playerFiles.*;
import GUI.gui;
import world.world;
import util.TrekkerMath;

public class hydra extends boss {
    public hydra(){
        setName("Hydra");
        setStrength(TrekkerMath.randomInt(14, 7) + (player.playerLevel/2 + world.AREANUM));
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
}
