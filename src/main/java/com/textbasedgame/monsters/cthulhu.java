package com.textbasedgame.monsters;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.*;
import com.textbasedgame.util.TrekkerMath;

public class cthulhu extends boss {
    public cthulhu(){
        setName("Cthulhu");
        setStrength(TrekkerMath.randomInt(20, 7));
        setOrigionalHealth((int)(TrekkerMath.randomDouble(3, 2.5) * (player.playerLevel)));
        setArmour((int)(TrekkerMath.randomDouble(3, 2.5)));
    }
    public void attackEffects(int damageDoneToPlayer){
        this.setHealth(this.getHealth() + damageDoneToPlayer/3);
        gui.printOnGameSide(getName() + " syphons " + damageDoneToPlayer/3 + " health from you!");
    }
    @Override
    public String attackString() {

        return "wraps you with its tenticles";
    }
}
