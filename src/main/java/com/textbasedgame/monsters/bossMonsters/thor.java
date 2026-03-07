package com.textbasedgame.monsters.bossMonsters;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.monsters.boss;
import com.textbasedgame.monsters.monsterCreator;
import com.textbasedgame.playerFiles.*;
import com.textbasedgame.util.TrekkerMath;
public class thor extends boss {
    public thor(){
        setName("Thor - God of Thunder");
        setStrength(monsterCreator.strongMonsterHealth(mLevel) + player.getStrength());
        setOriginalHealth(monsterCreator.strongMonsterHealth(mLevel) + player.playerLevel);
        setSpeed(monsterCreator.medMonsterSpeed(mLevel));
        setArmour((int)(TrekkerMath.randomDouble(2, 1.5)));
    }
    @Override
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
    public void bossIntro(){
        gui.printOnGameSide("Upon entering a massive thunderclap rings your ears");
        gui.printOnGameSide("A lightning bolt strikes the ground in front of you");
        gui.printOnGameSide("After blinking away the bright light a man stands in the singe mark");
        gui.printOnGameSide("He holds a massive hammer in his right hand");
        gui.printOnGameSide("He bellows something in a language you dont understand then charges");
    }
}
