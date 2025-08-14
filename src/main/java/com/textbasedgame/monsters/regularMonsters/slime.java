package com.textbasedgame.monsters.regularMonsters;

import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;

public class slime extends monster{
    public slime(){
        super.setName("Slime");
        super.setStrength(monsterCreator.weakMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.medMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.fastMonsterSpeed(mLevel));
    }
    
    @Override
    public String attackString(){
        return "squishes menacingly";
    }
}
