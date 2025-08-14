package com.textbasedgame.monsters.regularMonsters;

import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;

public  class skeleton extends monster {
    public skeleton(){
        super.setName("Skeleton");
        super.setStrength(monsterCreator.medMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.weakMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.slowMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "rattles his bones at you";
    }
}
