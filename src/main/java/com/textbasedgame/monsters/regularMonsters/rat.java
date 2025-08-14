package com.textbasedgame.monsters.regularMonsters;

import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;

public class rat extends monster{
    public rat(){
        super.setName("Rat");
        super.setStrength(monsterCreator.weakMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.weakMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.fastMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "bites down with its little teeth";
    }
}

