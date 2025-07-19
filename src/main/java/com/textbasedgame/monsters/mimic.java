package com.textbasedgame.monsters;
import com.textbasedgame.playerFiles.*;
import com.textbasedgame.util.*;
import com.textbasedgame.world.*;
public class mimic extends monster{
    public mimic(){
        super.setName("Mimic");
        super.setStrength(TrekkerMath.randomInt(7, 1));
        super.setOrigionalHealth((int)(TrekkerMath.randomDouble(1.34, .7) * (player.playerLevel + world.AREANUM + 10)));
        super.setSpeed(monsterCreator.fastMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "bites you with oversized teeth";
    }
}