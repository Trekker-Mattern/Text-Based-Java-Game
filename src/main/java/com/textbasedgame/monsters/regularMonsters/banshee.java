package com.textbasedgame.monsters.regularMonsters;

import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;

public  class banshee extends monster {
    public banshee(){
        super.setName("Banshee");
        super.setStrength(monsterCreator.medMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.weakMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.fastMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "screams and causes a shockwave knocking you back with incredible force";
    }
}
