package com.textbasedgame.monsters;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.*;
import com.textbasedgame.util.TrekkerMath;

public class cthulhu extends boss {
    public cthulhu(){
        setName("Cthulhu");
        setStrength(monsterCreator.strongMonsterHealth(mLevel) + player.getStrength());
        setOrigionalHealth(monsterCreator.strongMonsterHealth(mLevel) + player.playerLevel);
        setSpeed(monsterCreator.medMonsterSpeed(mLevel));
        setArmour((int)(TrekkerMath.randomDouble(2, 1.5)));
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
