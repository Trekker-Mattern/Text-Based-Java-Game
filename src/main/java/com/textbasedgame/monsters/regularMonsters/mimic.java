package com.textbasedgame.monsters.regularMonsters;

import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;

public class mimic extends monster{
    public mimic(){
        super.setName("Mimic");
        super.setStrength(monsterCreator.strongMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.medMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.fastMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "bites you with oversized teeth";
    }
}