package com.textbasedgame.monsters.bossMonsters;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.monsters.boss;
import com.textbasedgame.monsters.monsterCreator;
import com.textbasedgame.playerFiles.*;
import com.textbasedgame.util.TrekkerMath;

public class cthulhu extends boss {
    public cthulhu(){
        setName("Cthulhu");
        setStrength(monsterCreator.strongMonsterHealth(mLevel) + player.getStrength());
        setOriginalHealth(monsterCreator.strongMonsterHealth(mLevel) + player.playerLevel);
        setSpeed(monsterCreator.medMonsterSpeed(mLevel));
        setArmour((int)(TrekkerMath.randomDouble(2, 1.5)));
    }
    public void attackEffects(int damageDoneToPlayer){
        this.setHealth(this.getHealth() + damageDoneToPlayer/3);
        if(this.getHealth() > this.getMaxHealth()){
            this.setHealth(this.getMaxHealth());
        }
        gui.printOnGameSide(getName() + " syphons " + damageDoneToPlayer/3 + " health from you!");
    }
    @Override
    public String attackString() {
        return "wraps you with its tenticles";
    }

    @Override
    public void bossIntro() {
        gui.printOnGameSide("You enter a room and the air feels heavy. A droplet lands on your head. \n You brush it off and notice its too thick to be water.");
        if(player.getIntelligence() / (double)player.getPlayerLevel() > 0.25){
            gui.printOnGameSide("You notice tenticles creeping up the walls around you.");
            gui.printOnGameSide("From your extensive knowlege of monsters in the world you identify them as belonging to Cthulu");
            gui.printOnGameSide("With a great sigh you raise your" + player.LHand.toString() + "and ready yourself to fight the eater of worlds");
            this.setArmour((int)(getArmour() - getArmour()*.25));
        }
        gui.printOnGameSide("The beast rises from the floor and its many tenticles float in the air.");
        gui.printOnGameSide("It roars and readies itself for attack");
    }
}
