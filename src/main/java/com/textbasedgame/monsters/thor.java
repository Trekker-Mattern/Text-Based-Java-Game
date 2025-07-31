package com.textbasedgame.monsters;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.*;
import com.textbasedgame.util.TrekkerMath;
public class thor extends boss {
    public thor(){
        setName("Thor - God of Thunder");
        setStrength(monsterCreator.strongMonsterHealth(mLevel) + player.getStrength());
        setOrigionalHealth(monsterCreator.strongMonsterHealth(mLevel) + player.playerLevel);
        setSpeed(monsterCreator.medMonsterSpeed(mLevel));
        setArmour((int)(TrekkerMath.randomDouble(2, 1.5)));
    }
    public void attackEffects(int damageDoneToPlayer){
        if(player.equippedItems.stream().anyMatch( item -> item.tagsContains("Metal"))){
            gui.printOnGameSide("Thor's Lightning is attracted to your metal!");
            gui.printOnGameSide("Its power is amplified!");
        }
    }
    @Override
    public int getStrength(){
        if(player.equippedItems.stream().anyMatch( item -> item.tagsContains("Metal"))){
            return mStrength + (int)(mStrength/3);
        }
        else{
            return mStrength;
        }
    }

    @Override
    public String attackString() {

        return "strikes you with lightning";
    }

}
