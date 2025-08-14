package com.textbasedgame.monsters.regularMonsters;

import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;

public  class troll extends monster {
    public troll(){
        super.setName("Troll");
        super.setStrength(monsterCreator.strongMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.strongMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.slowMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "whacks you with a large club";
    }
}