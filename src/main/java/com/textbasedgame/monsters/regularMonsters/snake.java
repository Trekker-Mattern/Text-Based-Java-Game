package com.textbasedgame.monsters.regularMonsters;

import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;

public  class snake extends monster {
    public snake(){
        super.setName("Snake");

        
        super.setStrength(monsterCreator.weakMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.weakMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.fastMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "bites you really hard";
    }
}